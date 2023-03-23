package com.haohao.admin.module.system.mapper;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haohao.admin.module.system.entity.SysParamEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SysParamMapperTest {

    @Resource
    public SysParamMapper sysParamMapper;

    @Test
    void save() {
        SysParamEntity entity = new SysParamEntity();
        entity.setParamName("1");
        entity.setParamKey("label");
        entity.setParamValue("value");
        entity.setRemark("remark");
        sysParamMapper.insert(entity);
    }

    @Test
    void list() {
        sysParamMapper.selectList(Wrappers.emptyWrapper()).forEach(System.out::println);
    }

    @Test
    void page() {
        Page<SysParamEntity> page = sysParamMapper.selectPage(new Page<>(), Wrappers.emptyWrapper());
        page.getRecords().forEach(System.out::println);
    }
}