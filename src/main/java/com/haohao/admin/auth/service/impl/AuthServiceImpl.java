package com.haohao.admin.auth.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWTUtil;
import com.haohao.admin.auth.param.LoginParam;
import com.haohao.admin.auth.service.IAuthService;
import com.haohao.admin.common.constant.RedisConstant;
import com.haohao.admin.common.properties.JwtProperties;
import com.haohao.admin.common.result.ResultData;
import com.haohao.admin.common.util.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class AuthServiceImpl implements IAuthService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param loginParam 登录参数
     * @return result
     */
    @Override
    public ResultData<Map<String, Object>> login(LoginParam loginParam) {

        String username = loginParam.getUsername();
        String password = loginParam.getPassword();
        try {
            // 认证，该方法会自己调用UserDetailsServiceImpl.loadUserByUsername()
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            // 保存认证信息
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            // 获取认证信息
            UserDetailsImpl principal = (UserDetailsImpl) authenticate.getPrincipal();
            // 签发Token
            HashMap<String, Object> payload = new HashMap<>(1);
            payload.put("userId", principal.getUserId());
            String token = JWTUtil.createToken(payload, jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8));
            // 放入缓存
            stringRedisTemplate.opsForValue().set(RedisConstant.LOGIN_KEY + principal.getUserId(), JSONUtil.toJsonStr(principal), jwtProperties.getExpireTime(), TimeUnit.MINUTES);
            // 返回成功
            Map<String, Object> result = new HashMap<>(2);
            result.put("datetime", DateUtil.now());
            result.put("token", token);
            return ResultData.success(result);
        } catch (UsernameNotFoundException e) {
            return ResultData.error("用户不存在！");
        } catch (BadCredentialsException e) {
            return ResultData.error("密码错误！");
        } catch (DisabledException e) {
            return ResultData.error("账号被封禁！");
        } catch (AuthenticationException e) {
            log.error("login AuthenticationException is : \n", e);
            return ResultData.error(e.getMessage());
        } catch (Exception e) {
            log.error("login Exception is : \n", e);
            return ResultData.error(e.getMessage());
        }
    }

    /**
     * 登录用户信息
     *
     * @return result
     */
    @Override
    public ResultData<UserDetailsImpl> getInfo() {
        return ResultData.success(SecurityUtils.getUserDetails());
    }

    /**
     * 退出登录
     *
     * @return 退出结果
     */
    @Override
    public ResultData<String> logout() {
        // 删除缓存
        stringRedisTemplate.delete(String.valueOf(SecurityUtils.getUserId()));
        // 清空上下文
        SecurityContextHolder.clearContext();
        return ResultData.success();
    }
}
