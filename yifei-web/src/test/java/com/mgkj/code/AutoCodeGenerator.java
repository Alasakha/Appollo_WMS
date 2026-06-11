package com.mgkj.code;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public class AutoCodeGenerator {
    private static String driverClassName;
    private static String username;
    private static String password;
    private static String url;
    private static String author;
    private static String tableName;

    /**
     * 读取 application.properties 配置文件
     */
    public static void readProperty() throws IOException {
        YamlPropertiesFactoryBean yamlFactoryBean = new YamlPropertiesFactoryBean();
        yamlFactoryBean.setResources(new ClassPathResource("application-dev.yml"));
        Properties properties = yamlFactoryBean.getObject();

        // 正确获取属性并检查
        driverClassName = properties.getProperty("spring.datasource.dynamic.datasource.dscsys.driver-class-name");
        username = properties.getProperty("spring.datasource.dynamic.datasource.dscsys.username");
        password = properties.getProperty("spring.datasource.dynamic.datasource.dscsys.password");
        url = properties.getProperty("spring.datasource.dynamic.datasource.dscsys.url");
        author = properties.getProperty("author");
        tableName = properties.getProperty("MFG_QC_RESULT");
    }

    public static void main(String[] args) throws Exception {
        readProperty();
        String projectPath = "D:/WorkSpace/014/e10wmsAndBg"; // 代码生成到 e10wmsAndBg 下

        FastAutoGenerator.create(new DataSourceConfig.Builder(url, username, password))
                // 全局配置
                .globalConfig(builder -> builder
                        .author(author)
                        .outputDir(projectPath) // 生成代码的基础目录
                        .dateType(DateType.TIME_PACK))
                // 包配置
                .packageConfig(builder -> builder
                        .parent("com.mgkj") // 统一父包路径
                        .entity("entity")     // entity 包路径
                        .service("service")   // service 包路径
                        .serviceImpl("service.impl") // serviceImpl 包路径
                        .mapper("mapper")     // mapper 包路径
                        .controller("controller")) // controller 包路径
                // 自定义不同模块的文件输出路径
                .injectionConfig(builder -> {
                    Map<String, Object> pathMap = new HashMap<>();
                    pathMap.put("entityPath", projectPath + "/yifei-dao/src/main/java/com/mgkj/entity/");
                    pathMap.put("servicePath", projectPath + "/yifei-service/src/main/java/com/mgkj/service/");
                    pathMap.put("serviceImplPath", projectPath + "/yifei-service/src/main/java/com/mgkj/service/impl/");
                    pathMap.put("mapperPath", projectPath + "/yifei-service/src/main/java/com/mgkj/mapper/");
                    pathMap.put("controllerPath", projectPath + "/yifei-controller/src/main/java/com/mgkj/controller/");

                    builder.customMap(pathMap);
                })

                // 策略配置
                .strategyConfig(builder -> builder
//                        .addInclude(tableName.split("")) // 指定表名
                        .controllerBuilder()
                        .enableRestStyle()
                        .enableHyphenStyle()
                        .entityBuilder()
                        .idType(IdType.AUTO)
                        .naming(NamingStrategy.underline_to_camel)
                        .columnNaming(NamingStrategy.underline_to_camel)
                        .enableTableFieldAnnotation())
                // 使用 Freemarker 模板引擎
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}