package com.haohao.admin.module.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haohao.admin.module.system.entity.SysUserEntity;

/**
 * 系统用户 Mapper 接口
 *
 * @author haohao
 */
public interface SysUserMapper extends BaseMapper<SysUserEntity> {

    /**
     * 用户名查询用户
     * @param username 用户名
     * @return result
     */
    SysUserEntity selectUserByUsername(String username);

    /**
     * 禁用账号
     * @param username 用户名
     * @return result
     */
    int disableUsername(String username);
}
