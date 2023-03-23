package com.haohao.admin.module.system.service;

import com.haohao.admin.common.result.ResultData;
import com.haohao.admin.module.system.entity.SysRoleEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 系统角色 服务类
 *
 * @author haohao
 */
public interface ISysRoleService extends IService<SysRoleEntity> {

    /**
     * 添加角色
     *
     * @param sysRole 角色实体类
     * @return result
     */
    ResultData<String> saveRole(SysRoleEntity sysRole);

    /**
     * 更新角色
     *
     * @param sysRole 角色实体类
     * @return result
     */
    ResultData<String> updateRole(SysRoleEntity sysRole);
}
