package com.haohao.admin.common.config;

import com.haohao.admin.common.constant.RedisConstant;
import com.haohao.admin.module.system.entity.SysParamEntity;
import com.haohao.admin.module.system.service.ISysParamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * spring初始化
 */
@Slf4j
@Component
public class CustomApplicationRunner implements ApplicationRunner {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private ISysParamService sysParamService;

    @Override
    public void run(ApplicationArguments args) {
        // 初始化系统参数
        Long delete = stringRedisTemplate.delete(Objects.requireNonNull(stringRedisTemplate.keys(RedisConstant.PARAM_KEY + "*")));
        log.info("delete system redis param size {}", delete);
        List<SysParamEntity> list = sysParamService.list();
        list.forEach(item -> stringRedisTemplate.opsForValue().set(RedisConstant.PARAM_KEY + item.getParamKey(), item.getParamValue()));
        // TODO 初始化系统字典
    }
}
