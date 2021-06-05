package com.snk.userconumer.generator;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

//根据表名自动生成java文件
@Slf4j
public class Generator {

public static void main(String[] args) {
        String packageName = "com.snk.userconumer";
        boolean serviceNameStartWithI = false;
        generateByTables(serviceNameStartWithI, packageName, "Cai.ChangJun", "user-consumer", "broker_message_log");//li作者。test数据库名。user表名。
        log.info("completed...");
    }


    /**
     * @param serviceNameStartWithI
     * @param packageName   包名
     * @param author  作者
     * @param database  数据库名
     * @param tableNames 表名
     */
 private static void generateByTables(boolean serviceNameStartWithI, String packageName, String author, String database, String... tableNames) {
        GlobalConfig config = new GlobalConfig();
        String dbUrl = "jdbc:mysql://192.168.71.10:3306/" + database + "?useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername("root")
                .setPassword("root")
                .setDriverName("com.mysql.cj.jdbc.Driver");
        StrategyConfig strategyConfig = new StrategyConfig();
     strategyConfig.setNaming(NamingStrategy.underline_to_camel);
     strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
     strategyConfig.setEntityLombokModel(true);
     strategyConfig.setRestControllerStyle(true);
     //逻辑删除
//     strategyConfig.setLogicDeleteFieldName("is_delete");
     //乐观锁
//     strategyConfig.setVersionFieldName("version");
     //自动填充
     TableFill createTime = new TableFill("create_time", FieldFill.INSERT);
     TableFill updateTime = new TableFill("update_time", FieldFill.INSERT_UPDATE);
     ArrayList<TableFill> tableFills = new ArrayList<>();
     tableFills.add(createTime);
     tableFills.add(updateTime);
     strategyConfig.setTableFillList(tableFills);
     //设置restful风格的controller
     strategyConfig.setRestControllerStyle(true);
     //127.0.0.1:8080/hello_id_2
     strategyConfig.setControllerMappingHyphenStyle(true);
        strategyConfig
                .setCapitalMode(true)
//              .setSuperMapperClass("cn.saytime.mapper.BaseMapper")
                .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组
        config.setActiveRecord(false)
                .setAuthor(author)
                .setOutputDir("D:\\job\\spring-cloud-alibaba\\user-consumer\\src\\main\\java")//生成的java到文件夹下
                .setFileOverride(true)
                .setEnableCache(false);
        if (!serviceNameStartWithI) {
            config.setServiceName("%sService");
        }
        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setController("controller")
                                .setEntity("pojo.domain")
                                .setMapper("dao")
                                .setService("service")
                                .setServiceImpl("service.impl")
                                .setXml("mappers")
                ).execute();
    }


}
