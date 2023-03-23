package com.haohao.admin.module.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haohao.admin.common.result.ResultData;
import com.haohao.admin.module.system.entity.SysRoleEntity;
import com.haohao.admin.module.system.mapper.SysRoleMapper;
import com.haohao.admin.module.system.service.ISysRoleService;
import org.springframework.stereotype.Service;

/**
 * 系统角色 服务实现类
 *
 * @author haohao
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRoleEntity> implements ISysRoleService {

    /**
     * 添加角色
     *
     * @param sysRole 角色实体类
     * @return result
     */
    @Override
    public ResultData<String> saveRole(SysRoleEntity sysRole) {
        return ResultData.flag(save(sysRole));
    }

    /**
     * 更新角色
     *
     * @param sysRole 角色实体类
     * @return result
     */
    @Override
    public ResultData<String> updateRole(SysRoleEntity sysRole) {
        return ResultData.flag(updateById(sysRole));
    }
}
