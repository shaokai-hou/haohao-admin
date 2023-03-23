package com.haohao.admin.module.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.haohao.admin.common.bean.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 角色用户关联表
 */
@Data
@TableName("sys_role_user")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "角色用户关联表")
public class SysRoleUserEntity extends BaseBean {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("角色ID")
    private Long roleId;

    @ApiModelProperty("用户ID")
    private Long userId;
}
