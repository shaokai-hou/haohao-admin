package com.haohao.admin.auth.config;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWTUtil;
import com.haohao.admin.auth.service.impl.UserDetailsImpl;
import com.haohao.admin.common.constant.RedisConstant;
import com.haohao.admin.common.properties.JwtProperties;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Resource
    private JwtProperties jwtProperties;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        // 拿到Authorization请求头内的信息
        String token = request.getHeader(jwtProperties.getHeader());
        // 判断不为空、指定字符串开头
        if (StrUtil.isNotBlank(token) && StrUtil.startWith(token, jwtProperties.getPrefix())) {
            // 获取真实token、验证token
            String realToken = token.substring(jwtProperties.getPrefix().length()).trim();
            if (JWTUtil.verify(realToken, jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8))) {
                // token获取userId
                Integer userId = (Integer) JWTUtil.parseToken(realToken).getPayload("userId");
                if (Objects.nonNull(userId) && Boolean.TRUE.equals(stringRedisTemplate.hasKey(RedisConstant.LOGIN_KEY + userId))) {
                    // 查询缓存用户信息
                    String cacheStr = stringRedisTemplate.opsForValue().get(RedisConstant.LOGIN_KEY + userId);
                    UserDetailsImpl userDetails = JSONUtil.toBean(cacheStr, UserDetailsImpl.class);
                    // 组装authentication对象，构造参数是Principal Credentials 与 Authorities
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
                    // 将authentication信息放入到上下文对象中
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        chain.doFilter(request, response);
    }
}
