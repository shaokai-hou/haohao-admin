package com.haohao.admin.module.system.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.haohao.admin.common.bean.BaseBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName("sys_base")
@EqualsAndHashCode(callSuper = true)
public class SysBaseEntity extends BaseBean {

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String name;
}
