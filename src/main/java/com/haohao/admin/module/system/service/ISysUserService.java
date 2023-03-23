package com.haohao.admin.module.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.haohao.admin.common.result.ResultData;
import com.haohao.admin.module.system.entity.SysUserEntity;

public interface ISysUserService extends IService<SysUserEntity> {

    /**
     * 用户名获取用户
     * @param username 用户名称
     * @return result
     */
    SysUserEntity getUserByUsername(String username);

    /**
     * 保存用户
     *
     * @param sysUser 用户实体类
     * @return result
     */
    ResultData<String> saveUser(SysUserEntity sysUser);

    /**
     * 修改用户
     * @param sysUser 用户实体类
     * @return result
     */
    ResultData<String> updateUser(SysUserEntity sysUser);

    /**
     * 重置密码
     * @param ids 用户IDS
     * @return result
     */
    ResultData<String> resetPassword(String ids);
}
