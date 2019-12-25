package com.guli.statistics;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

/**
 * @author Jason
 * @create 2019-12-08-13:14
 */


public class CodeGenerator {

    @Test
    public void genCode() {

        //创建代码生成器
        AutoGenerator mpg = new AutoGenerator();

        /*
        创建全局配置
        1、获取当前目录路径
        2、设置输出的路径
        3、设置作者
        4、生成代码后是否打开资源管理器
        5、生成代码后是否覆盖原有的代码
        6、取消生成的Service中的I
        7、设置主键策略
        8、设置生成的日期的类型
        9、是否开启Swagger2模式
         */
        GlobalConfig globalConfig = new GlobalConfig();
        String path = System.getProperty("user.dir");
        globalConfig.setOutputDir(path + "/src/main/java");
        globalConfig.setAuthor("jason");
        globalConfig.setOpen(false);
        globalConfig.setFileOverride(false);
        globalConfig.setServiceName("%sService");
        globalConfig.setIdType(IdType.ID_WORKER_STR);
        globalConfig.setDateType(DateType.ONLY_DATE);
        globalConfig.setSwagger2(true);

        mpg.setGlobalConfig(globalConfig);

        /**
         *数据源的配置
         */
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("123456");
        dataSourceConfig.setUrl("jdbc:mysql://192.168.149.132:3306/guli_edu");
        dataSourceConfig.setDbType(DbType.MYSQL);

        mpg.setDataSource(dataSourceConfig);

        /**
         * 包配置
         */
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setModuleName("statistics");
        packageConfig.setParent("com.guli");
        packageConfig.setController("controller");
        packageConfig.setService("service");
        packageConfig.setEntity("entity");
        packageConfig.setMapper("mapper");

        mpg.setPackageInfo(packageConfig);

        /**
         * 策略配置,
         * 1、需要生成代码的表的名字，可以填写多个
         * 2、表名和字段名的生成策略。当前设置的是驼峰
         * 3、表前缀不生成
         * 4、开启lombok注解
         * 5、设置逻辑删除的字段
         * 6、去掉布尔值的is_前缀
         */
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setInclude("statistics_daily");
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setTablePrefix(packageConfig.getModuleName() + "_");
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setEntityLombokModel(true);

        strategyConfig.setLogicDeleteFieldName("is_deleted");
        strategyConfig.setEntityBooleanColumnRemoveIsPrefix(true);

        mpg.setStrategy(strategyConfig);


        mpg.execute();

    }
}
