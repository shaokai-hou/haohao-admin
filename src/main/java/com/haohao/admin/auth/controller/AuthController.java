package com.haohao.admin.auth.controller;

import cn.hutool.core.date.DateUtil;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.haohao.admin.auth.param.LoginParam;
import com.haohao.admin.auth.service.impl.AuthServiceImpl;
import com.haohao.admin.auth.service.impl.UserDetailsImpl;
import com.haohao.admin.common.result.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@Api(tags = "权限 API")
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    public AuthServiceImpl authService;

    @PostMapping("/login")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "登录")
    public ResultData<Map<String, Object>> login(@RequestBody LoginParam param) {
        log.info("登录请求 参数:{} 时间:{}", param, DateUtil.now());
        return authService.login(param);
    }

    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "退出登录")
    @GetMapping("/logout")
    public ResultData<String> logout() {
        return authService.logout();
    }

    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "登录信息")
    @GetMapping("/info")
    public ResultData<UserDetailsImpl> getInfo() {
        return authService.getInfo();
    }
}
