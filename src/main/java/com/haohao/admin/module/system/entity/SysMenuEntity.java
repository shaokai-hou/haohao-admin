package com.haohao.admin.module.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.haohao.admin.common.bean.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@TableName("sys_menu")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统参数实体类")
public class SysMenuEntity extends BaseBean {

    @ApiModelProperty(value = "父ID", required = true)
    private Long parentId;

    @ApiModelProperty(value = "菜单名称", required = true)
    private String title;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "菜单类型（1:目录 2:菜单 3:权限）", required = true)
    private Integer type;

    @ApiModelProperty(value = "请求地址")
    private String path;

    @ApiModelProperty(value = "组件")
    private String component;

    @ApiModelProperty(value = "权限")
    private String permissions;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @TableField(exist = false)
    @ApiModelProperty(value = "孩子菜单")
    private List<SysMenuEntity> children;
}
