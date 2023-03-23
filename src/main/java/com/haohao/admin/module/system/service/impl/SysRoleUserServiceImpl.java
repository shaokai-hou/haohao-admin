package com.haohao.admin.module.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haohao.admin.module.system.entity.SysRoleUserEntity;
import com.haohao.admin.module.system.mapper.SysRoleUserMapper;
import com.haohao.admin.module.system.service.ISysRoleUserService;
import org.springframework.stereotype.Service;

/**
 * 角色用户关联表 服务实现类
 */
@Service
public class SysRoleUserServiceImpl extends ServiceImpl<SysRoleUserMapper, SysRoleUserEntity> implements ISysRoleUserService {
}
