package com.haohao.admin.module.system.mapper;

import com.haohao.admin.module.system.entity.SysUserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SysUserMapperTest {

    @Autowired
    public SysUserMapper sysUserMapper;

    @Test
    void save() {
        SysUserEntity entity = new SysUserEntity();
        entity.setUsername("1");
        sysUserMapper.insert(entity);
    }
}