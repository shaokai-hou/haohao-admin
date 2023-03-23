package com.haohao.admin.auth.listener;

import com.haohao.admin.common.constant.RedisConstant;
import com.haohao.admin.module.system.mapper.SysUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 密码错误监听类
 */
@Slf4j
@Component
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RedisTemplate<String, Integer> integerRedisTemplate;

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
        String username = event.getAuthentication().getPrincipal().toString();
        String redisKey = RedisConstant.LOGIN_FAIL_KEY + username;

        integerRedisTemplate.opsForValue().increment(RedisConstant.LOGIN_FAIL_KEY + username, 1);
        int redisCount = Objects.requireNonNull(integerRedisTemplate.opsForValue().get(redisKey));
        String redisParamKey = Objects.requireNonNull(stringRedisTemplate.opsForValue().get("param:key:account_error_count"));
        if (redisCount >= Integer.parseInt(redisParamKey)) {
            // 锁定账号、删除key
            sysUserMapper.disableUsername(username);
            integerRedisTemplate.delete(redisKey);
            log.error("账号被锁定了！username:{}", username);
        }
    }
}
