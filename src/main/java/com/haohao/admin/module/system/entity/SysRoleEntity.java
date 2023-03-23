package com.haohao.admin.module.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.haohao.admin.common.bean.BaseBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@TableName("sys_role")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SysRoleEntity extends BaseBean {

    @NotBlank(message = "角色名称不能为空～")
    @ApiModelProperty(value = "角色名称", required = true)
    private String roleName;

    @NotBlank(message = "角色编码不能为空～")
    @ApiModelProperty(value = "角色编码", required = true)
    private String roleCode;

    @NotNull(message = "排序不能为空～")
    @ApiModelProperty(value = "排序", required = true)
    private Integer sort;
}
