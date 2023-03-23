package com.haohao.admin.module.system.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.haohao.admin.common.bean.BaseBean;
import com.haohao.admin.common.constant.SeparatorConstant;
import com.haohao.admin.common.result.ResultData;
import com.haohao.admin.module.system.entity.SysUserEntity;
import com.haohao.admin.module.system.service.impl.SysUserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "用户 API")
@RestController
@RequestMapping("/system/user")
public class SysUserController {

    @Resource
    private SysUserServiceImpl sysUserService;

    @ApiOperationSupport(order = 1, author = "haohao")
    @ApiOperation(value = "分页")
    @GetMapping("/page")
    public ResultData<Page<SysUserEntity>> page(Page<SysUserEntity> page, SysUserEntity sysUser) {
        LambdaQueryWrapper<SysUserEntity> queryWrapper = Wrappers.<SysUserEntity>lambdaQuery()
                .like(StrUtil.isNotBlank(sysUser.getNickname()), SysUserEntity::getNickname, sysUser.getNickname())
                .like(StrUtil.isNotBlank(sysUser.getUsername()), SysUserEntity::getUsername, sysUser.getUsername())
                .ne(SysUserEntity::getUsername, "admin")
                .orderByDesc(BaseBean::getCreateTime);
        return ResultData.success(sysUserService.page(page, queryWrapper));
    }

    @ApiOperationSupport(order = 2, author = "haohao")
    @ApiOperation(value = "列表")
    @GetMapping("/list")
    public ResultData<List<SysUserEntity>> list() {
        return ResultData.success(sysUserService.list(Wrappers.emptyWrapper()));
    }

    @ApiOperationSupport(order = 3, author = "haohao")
    @ApiOperation(value = "新增")
    @PostMapping
    public ResultData<String> save(@RequestBody @Valid SysUserEntity sysUser) {
        return sysUserService.saveUser(sysUser);
    }

    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "修改")
    @PutMapping
    public ResultData<String> update(@RequestBody SysUserEntity sysUser) {
        return sysUserService.updateUser(sysUser);
    }

    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "删除")
    @DeleteMapping("/{ids}")
    @ApiImplicitParam(name = "ids", value = "参数ID，多个用逗号分隔", required = true)
    public ResultData<String> delete(@PathVariable("ids") String ids) {
        List<String> split = StrUtil.split(ids, SeparatorConstant.SEPARATOR_DOU_HAO, true, true);
        List<Long> tmpIds = split.stream().map(Long::parseLong).collect(Collectors.toList());
        return ResultData.flag(sysUserService.removeByIds(tmpIds));
    }

    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "重置密码")
    @PutMapping("/reset/password/{ids}")
    @ApiImplicitParam(name = "ids", value = "参数ID，多个用逗号分隔", required = true)
    public ResultData<String> resetPassword(@PathVariable("ids") String ids) {
        return sysUserService.resetPassword(ids);
    }
}
