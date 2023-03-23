package com.haohao.admin.module.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.haohao.admin.common.result.ResultData;
import com.haohao.admin.module.system.entity.SysParamEntity;
import com.haohao.admin.module.system.service.ISysParamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Api(tags = "参数 API")
@RestController
@RequestMapping("/system/param")
public class SysParamController {

    @Resource
    private ISysParamService sysParamService;

    @ApiOperationSupport(order = 1, author = "haohao")
    @ApiOperation(value = "分页")
    @GetMapping("/page")
    public ResultData<IPage<SysParamEntity>> page(Page<SysParamEntity> page, SysParamEntity sysParam) {
        return ResultData.success(sysParamService.selectPage(page, sysParam));
    }

    @ApiOperationSupport(order = 2, author = "haohao")
    @ApiOperation(value = "列表")
    @GetMapping("/list")
    public ResultData<List<SysParamEntity>> list() {
        return ResultData.success(sysParamService.list(Wrappers.emptyWrapper()));
    }

    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "详情")
    @GetMapping("/detail/{id}")
    public ResultData<SysParamEntity> detail(@PathVariable("id") Long id) {
        return ResultData.success(sysParamService.getById(id));
    }

    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "新增")
    @PostMapping
    public ResultData<String> save(@RequestBody @Valid SysParamEntity sysParam) {
        return sysParamService.saveParam(sysParam);
    }

    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "修改")
    @PutMapping
    public ResultData<String> update(@RequestBody SysParamEntity sysParam) {
        return sysParamService.updateParam(sysParam);
    }

    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "删除")
    @DeleteMapping("/{ids}")
    public ResultData<String> delete(@PathVariable("ids") String ids) {
        return ResultData.flag(sysParamService.deleteParam(ids));
    }
}
