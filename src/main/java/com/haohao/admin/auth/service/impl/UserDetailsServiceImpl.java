package com.haohao.admin.auth.service.impl;

import com.haohao.admin.module.system.entity.SysUserEntity;
import com.haohao.admin.module.system.service.impl.SysUserServiceImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private SysUserServiceImpl sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserEntity entity = sysUserService.getUserByUsername(username);
        if (Objects.isNull(entity)) {
            throw new UsernameNotFoundException("User Not Found");
        }
        // TODO 查询角色、权限
        Set<String> roles = new HashSet<>();
        Set<String> permissions = new HashSet<>();
        return UserDetailsImpl.builder().userId(entity.getId()).sysUser(entity).roles(roles).permissions(permissions).build();
    }
}
