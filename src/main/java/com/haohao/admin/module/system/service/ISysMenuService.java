package com.haohao.admin.module.system.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.haohao.admin.module.system.entity.SysMenuEntity;

import java.util.List;

/**
 * 系统菜单表 服务类
 *
 * @author haohao
 */
public interface ISysMenuService extends IService<SysMenuEntity> {

    /**
     * 分页查询
     *
     * @param page    分页对象
     * @param sysMenu 查询对象
     * @return result
     */
    Page<SysMenuEntity> selectPage(Page<SysMenuEntity> page, SysMenuEntity sysMenu);

    /**
     * 树形菜单
     * @return result
     */
    List<Tree<Long>> getTreeList();
}
