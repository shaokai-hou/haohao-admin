package com.haohao.admin.module.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.haohao.admin.common.bean.BaseBean;
import com.haohao.admin.common.constant.RedisConstant;
import com.haohao.admin.common.constant.SeparatorConstant;
import com.haohao.admin.common.result.ResultData;
import com.haohao.admin.module.system.entity.SysParamEntity;
import com.haohao.admin.module.system.mapper.SysParamMapper;
import com.haohao.admin.module.system.service.ISysParamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SysParamServiceImpl extends ServiceImpl<SysParamMapper, SysParamEntity> implements ISysParamService {

    /**
     * 分页查询
     *
     * @param page     分页对象
     * @param sysParam 参数
     * @return result
     */
    @Override
    public IPage<SysParamEntity> selectPage(IPage<SysParamEntity> page, SysParamEntity sysParam) {
        LambdaQueryWrapper<SysParamEntity> queryWrapper = Wrappers.<SysParamEntity>lambdaQuery()
                .like(StrUtil.isNotBlank(sysParam.getParamKey()), SysParamEntity::getParamKey, sysParam.getParamKey())
                .like(StrUtil.isNotBlank(sysParam.getParamName()), SysParamEntity::getParamName, sysParam.getParamName())
                .orderByAsc(SysParamEntity::getSort)
                .orderByDesc(BaseBean::getCreateTime);
        return baseMapper.selectPage(page, queryWrapper);
    }

    /**
     * 保存参数
     *
     * @param sysParam 实体类
     * @return result
     */
    @Override
    public ResultData<String> saveParam(SysParamEntity sysParam) {
        long count = count(Wrappers.<SysParamEntity>lambdaQuery().eq(SysParamEntity::getParamKey, sysParam.getParamKey()));
        if (SqlHelper.retBool(count)) {
            return ResultData.error("参数KEY已存在！");
        }
        return ResultData.flag(save(sysParam));
    }

    /**
     * 修改参数
     *
     * @param sysParam 实体类
     * @return result
     */
    @Override
    public ResultData<String> updateParam(SysParamEntity sysParam) {
        SysParamEntity entity = getById(sysParam.getId());
        entity.setParamName(sysParam.getParamName());
        entity.setParamValue(sysParam.getParamValue());
        entity.setSort(sysParam.getSort());
        entity.setRemark(sysParam.getRemark());
        return ResultData.flag(updateById(entity));
    }

    /**
     * ID删除参数
     *
     * @param ids 逗号分隔的ID字符串
     * @return result
     */
    @Override
    public Boolean deleteParam(String ids) {
        List<String> split = StrUtil.split(ids, SeparatorConstant.SEPARATOR_DOU_HAO, true, true);
        List<Long> tmpIds = split.stream().map(Long::parseLong).collect(Collectors.toList());
        return removeByIds(tmpIds);
    }
}
