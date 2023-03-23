package com.haohao.admin.module.system.controller;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.haohao.admin.common.result.ResultData;
import com.haohao.admin.module.system.entity.SysMenuEntity;
import com.haohao.admin.module.system.service.impl.SysMenuServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 系统菜单 前端控制器
 *
 * @author haohao
 */
@Api(tags = "菜单 API")
@RestController
@RequestMapping("/system/menu")
public class SysMenuController {

    @Resource
    private SysMenuServiceImpl sysMenuService;

    @ApiOperationSupport(order = 1, author = "haohao")
    @ApiOperation(value = "分页")
    @GetMapping("/page")
    public ResultData<Page<SysMenuEntity>> page(Page<SysMenuEntity> page, SysMenuEntity sysMenu) {
        return ResultData.success(sysMenuService.selectPage(page, sysMenu));
    }

    @ApiOperationSupport(order = 2, author = "haohao")
    @ApiOperation(value = "列表")
    @GetMapping("/list")
    public ResultData<List<SysMenuEntity>> list() {
        return ResultData.success(sysMenuService.list(Wrappers.emptyWrapper()));
    }

    @ApiOperationSupport(order = 3, author = "haohao")
    @ApiOperation(value = "树")
    @GetMapping("/tree")
    public ResultData<List<Tree<Long>>> tree() {
        return ResultData.success(sysMenuService.getTreeList());
    }
}
