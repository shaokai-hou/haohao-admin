package com.haohao.admin.module.system.mapper;


import com.haohao.admin.module.system.entity.SysBaseEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SysBaseMapperTest {

    @Autowired
    public SysBaseMapper sysBaseMapper;

    @Test
    void save() {
        SysBaseEntity entity = new SysBaseEntity();
        entity.setName("1");
        sysBaseMapper.insert(entity);
    }

    @Test
    void update() {
        SysBaseEntity entity = sysBaseMapper.selectById(1L);
        entity.setName(null);
        sysBaseMapper.updateById(entity);
    }
}