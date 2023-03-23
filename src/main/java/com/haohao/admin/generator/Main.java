package com.haohao.admin.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.haohao.admin.common.bean.BaseBean;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/haohao_admin";
        String username = "root";
        String password = "root";
        List<String> tables = Arrays.asList("sys_role_user");


        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder
                            .author("haohao")
                            .disableOpenDir() // 不要打开文件夹
                            .enableSwagger() // 开启swagger
                            .outputDir("/Users/haohao/temp/代码生成"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.haohao.admin.module") // 设置父包名
                            .moduleName("system") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "/Users/haohao/temp/代码生成/com/haohao/admin/module/system/mapper")); // 设置mapperXml生成路径
                })
                .templateConfig(builder -> {
                })
                .strategyConfig(builder -> {
                    builder.entityBuilder() // 实体类策略
                            .superClass(BaseBean.class) // 父类
                            .enableFileOverride()  // 开启覆盖
                            .formatFileName("%sEntity");
                    builder.controllerBuilder() // 控制器类策略
                            .enableFileOverride()
                            .enableRestStyle();

                    builder.serviceBuilder()
                            .enableFileOverride();

                    builder.mapperBuilder()
                            .enableFileOverride();

                    builder.entityBuilder()
                            .enableFileOverride();

                    builder.addInclude(tables); // 设置需要生成的表名
                })
                .execute();
    }
}
