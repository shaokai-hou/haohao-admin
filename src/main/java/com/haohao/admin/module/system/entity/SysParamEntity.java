package com.haohao.admin.module.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.haohao.admin.common.bean.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@TableName("sys_param")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统参数实体类")
public class SysParamEntity extends BaseBean implements Serializable {

    @ApiModelProperty(value = "参数名称", required = true)
    private String paramName;

    @NotBlank(message = "参数key不能为空～")
    @ApiModelProperty(value = "参数key", required = true)
    private String paramKey;

    @ApiModelProperty(value = "参数value", required = true)
    private String paramValue;

    @ApiModelProperty(value = "排序", required = true)
    private Integer sort;

    @ApiModelProperty(value = "备注", required = true)
    private String remark;
}
