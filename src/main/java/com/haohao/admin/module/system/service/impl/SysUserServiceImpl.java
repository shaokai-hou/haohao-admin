package com.haohao.admin.module.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.haohao.admin.common.bean.BaseBean;
import com.haohao.admin.common.constant.RedisConstant;
import com.haohao.admin.common.constant.SeparatorConstant;
import com.haohao.admin.common.result.ResultData;
import com.haohao.admin.common.util.SecurityUtils;
import com.haohao.admin.module.system.entity.SysUserEntity;
import com.haohao.admin.module.system.mapper.SysUserMapper;
import com.haohao.admin.module.system.service.ISysUserService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity> implements ISysUserService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 用户名获取用户
     *
     * @param username 用户名称
     * @return result
     */
    @Override
    @Cacheable(cacheNames = RedisConstant.USER_MODULE, key = "#username", sync = true)
    public SysUserEntity getUserByUsername(String username) {
        return baseMapper.selectUserByUsername(username);
    }

    /**
     * 保存用户
     *
     * @param sysUser 用户实体类
     * @return result
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultData<String> saveUser(SysUserEntity sysUser) {
        // 用户名（username）唯一验证
        long count = count(Wrappers.<SysUserEntity>lambdaQuery().eq(SysUserEntity::getUsername, sysUser.getUsername().trim()));
        if (SqlHelper.retBool(count)) {
            return ResultData.error("用户名已存在!");
        }
        sysUser.setPassword(SecurityUtils.passwordEncode(sysUser.getPassword()));
        sysUser.setState(0);
        return ResultData.flag(save(sysUser));
    }

    /**
     * 修改用户
     *
     * @param sysUser 用户实体类
     * @return result
     */
    @Override
    public ResultData<String> updateUser(SysUserEntity sysUser) {
        SysUserEntity entity = getById(sysUser.getId());
        entity.setNickname(sysUser.getNickname());
        entity.setPhone(sysUser.getPhone());
        entity.setState(sysUser.getState());
        entity.setAvatar(sysUser.getAvatar());
        return ResultData.flag(updateById(entity));
    }

    /**
     * 重置密码
     *
     * @param ids 用户IDS
     * @return result
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultData<String> resetPassword(String ids) {
        String initPassword = stringRedisTemplate.opsForValue().get("init_password");
        // 构建参数
        List<String> split = StrUtil.split(ids, SeparatorConstant.SEPARATOR_DOU_HAO, true, true);
        List<Long> tmpIds = split.stream().map(Long::parseLong).collect(Collectors.toList());
        // 更新密码
        List<SysUserEntity> list = list(Wrappers.<SysUserEntity>lambdaQuery().in(BaseBean::getId, tmpIds));
        list.forEach(user -> user.setPassword(SecurityUtils.passwordEncode(initPassword)));
        return ResultData.flag(updateBatchById(list));
    }
}
