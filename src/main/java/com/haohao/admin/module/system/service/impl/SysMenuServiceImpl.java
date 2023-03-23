package com.haohao.admin.module.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haohao.admin.module.system.entity.SysMenuEntity;
import com.haohao.admin.module.system.mapper.SysMenuMapper;
import com.haohao.admin.module.system.service.ISysMenuService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统菜单表 服务实现类
 * </p>
 *
 * @author haohao
 * @since 2023-03-20
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenuEntity> implements ISysMenuService {

    /**
     * 分页查询
     *
     * @param page    分页对象
     * @param sysMenu 查询对象
     * @return result
     */
    @Override
    public Page<SysMenuEntity> selectPage(Page<SysMenuEntity> page, SysMenuEntity sysMenu) {
        List<SysMenuEntity> list = list(Wrappers.<SysMenuEntity>lambdaQuery()
                .like(StrUtil.isNotBlank(sysMenu.getTitle()), SysMenuEntity::getTitle, sysMenu.getTitle())
                .in(SysMenuEntity::getType, 1, 2, 3));
        //树配置
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        treeNodeConfig.setWeightKey("sort");
        treeNodeConfig.setDeep(3);
        //构建树
        List<Tree<Long>> treeList = TreeUtil.build(list, 0L, treeNodeConfig, ((treeNode, tree) -> {
            tree.setId(treeNode.getId());
            tree.setParentId(treeNode.getParentId());
            tree.putExtra("path", treeNode.getPath());
            tree.putExtra("component", treeNode.getComponent());
            tree.putExtra("title", treeNode.getTitle());
            tree.putExtra("type", treeNode.getType());
            tree.putExtra("sort", treeNode.getSort());
            tree.putExtra("createTime", treeNode.getCreateTime());
        }));
        page.setTotal(count(Wrappers.<SysMenuEntity>lambdaQuery().eq(SysMenuEntity::getType, 1)));
        return page.setRecords(BeanUtil.copyToList(treeList, SysMenuEntity.class));
    }

    /**
     * 树形菜单
     *
     * @return result
     */
    @Override
    public List<Tree<Long>> getTreeList() {
        List<SysMenuEntity> list = list(Wrappers.<SysMenuEntity>lambdaQuery().in(SysMenuEntity::getType, 1, 2));
        //树配置
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        treeNodeConfig.setWeightKey("sort");
        treeNodeConfig.setDeep(3);
        //构建树
        return TreeUtil.build(list, 0L, treeNodeConfig, ((treeNode, tree) -> {
            tree.setId(treeNode.getId());
            tree.setParentId(treeNode.getParentId());
            tree.putExtra("path", treeNode.getPath());
            tree.putExtra("component", treeNode.getComponent());
            Map<String, String> meta = new HashMap<>(2);
            meta.put("icon", treeNode.getIcon());
            meta.put("title", treeNode.getTitle());
            tree.putExtra("meta", meta);
        }));
    }
}
