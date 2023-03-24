package com.haohao.admin.module.system.controller;

import com.haohao.admin.common.result.ResultData;
import com.haohao.admin.module.system.entity.SysUserEntity;
import org.databene.contiperf.PerfTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class SysUserControllerTest {

    @Resource
    private SysUserController controller;

    @Test
    void page() {
    }

    @Test
    @PerfTest(threads = 30, duration = 1000)
    void list() {
        ResultData<List<SysUserEntity>> list = controller.list();
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void resetPassword() {
    }
}