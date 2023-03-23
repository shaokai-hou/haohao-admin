package com.haohao.admin.module.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.haohao.admin.common.result.ResultData;
import com.haohao.admin.module.system.entity.SysParamEntity;

public interface ISysParamService extends IService<SysParamEntity> {

    /**
     * 分页查询
     * @param page 分页对象
     * @param sysParam 参数
     * @return result
     */
    IPage<SysParamEntity> selectPage(IPage<SysParamEntity> page, SysParamEntity sysParam);

    /**
     * 保存参数
     *
     * @param sysParam 实体类
     * @return result
     */
    ResultData<String> saveParam(SysParamEntity sysParam);

    /**
     * 修改参数
     *
     * @param sysParam 实体类
     * @return result
     */
    ResultData<String> updateParam(SysParamEntity sysParam);

    /**
     * ID删除参数
     *
     * @param ids 逗号分隔的ID字符串
     * @return result
     */
    Boolean deleteParam(String ids);
}
