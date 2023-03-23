package com.haohao.admin.module.system.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.haohao.admin.common.bean.BaseBean;
import com.haohao.admin.common.constant.SeparatorConstant;
import com.haohao.admin.common.result.ResultData;
import com.haohao.admin.module.system.entity.SysRoleEntity;
import com.haohao.admin.module.system.service.impl.SysRoleServiceImpl;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统角色 前端控制器
 *
 * @author haohao
 */
@RestController
@RequestMapping("/system/role")
public class SysRoleController {

    @Resource
    private SysRoleServiceImpl sysRoleService;

    @ApiOperationSupport(order = 1, author = "haohao")
    @ApiOperation(value = "分页")
    @GetMapping("/page")
    public ResultData<Page<SysRoleEntity>> page(Page<SysRoleEntity> page, SysRoleEntity sysRole) {
        LambdaQueryWrapper<SysRoleEntity> queryWrapper = Wrappers.<SysRoleEntity>lambdaQuery()
                .like(StrUtil.isNotBlank(sysRole.getRoleName()), SysRoleEntity::getRoleName, sysRole.getRoleName())
                .orderByAsc(SysRoleEntity::getSort)
                .orderByDesc(BaseBean::getCreateTime);
        return ResultData.success(sysRoleService.page(page, queryWrapper));
    }

    @ApiOperationSupport(order = 2, author = "haohao")
    @ApiOperation(value = "列表")
    @GetMapping("/list")
    public ResultData<List<SysRoleEntity>> list() {
        return ResultData.success(sysRoleService.list(Wrappers.emptyWrapper()));
    }

    @ApiOperationSupport(order = 3, author = "haohao")
    @ApiOperation(value = "新增")
    @PostMapping
    public ResultData<String> save(@RequestBody @Valid SysRoleEntity sysRole) {
        return sysRoleService.saveRole(sysRole);
    }

    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "修改")
    @PutMapping
    public ResultData<String> update(@RequestBody SysRoleEntity sysRole) {
        return sysRoleService.updateRole(sysRole);
    }

    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "删除")
    @DeleteMapping("/{ids}")
    @ApiImplicitParam(name = "ids", value = "参数ID，多个用逗号分隔", required = true)
    public ResultData<String> delete(@PathVariable("ids") String ids) {
        List<String> split = StrUtil.split(ids, SeparatorConstant.SEPARATOR_DOU_HAO, true, true);
        List<Long> tmpIds = split.stream().map(Long::parseLong).collect(Collectors.toList());
        return ResultData.flag(sysRoleService.removeByIds(tmpIds));
    }
}
