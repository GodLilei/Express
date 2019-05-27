package com.myproject.demo.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.myproject.demo.utils.DatabaseType;
import com.myproject.demo.utils.DynamicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration // 该注解类似于spring配置文件
public class MyBatisConfig implements EnvironmentAware {

    private Environment environment;

    @Override
    public void setEnvironment(final Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource mysqlDatasource() throws Exception {
        Properties props = new Properties();
        props.put("driverClassName", environment.getProperty("mysql.datasource.driver-class-name"));
        props.put("url", environment.getProperty("mysql.datasource.url"));
        props.put("username", environment.getProperty("mysql.datasource.username"));
        props.put("password", environment.getProperty("mysql.datasource.password"));
        return DruidDataSourceFactory.createDataSource(props);
    }

    @Bean
    public DataSource uatstlDatasource() throws Exception {
        Properties props = new Properties();
        props.put("driverClassName", environment.getProperty("uatstl.datasource.driver-class-name"));
        props.put("url", environment.getProperty("uatstl.datasource.url"));
        props.put("username", environment.getProperty("uatstl.datasource.username"));
        props.put("password", environment.getProperty("uatstl.datasource.password"));
        return DruidDataSourceFactory.createDataSource(props);
    }

    @Bean
    public DataSource frontdbDatasource() throws Exception {
        Properties props = new Properties();
        props.put("driverClassName", environment.getProperty("frontdb.datasource.driver-class-name"));
        props.put("url", environment.getProperty("frontdb.datasource.url"));
        props.put("username", environment.getProperty("frontdb.datasource.username"));
        props.put("password", environment.getProperty("frontdb.datasource.password"));
        return DruidDataSourceFactory.createDataSource(props);
    }

    @Bean
    public DataSource olduatDatasource() throws Exception {
        Properties props = new Properties();
        props.put("driverClassName", environment.getProperty("olduat.datasource.driver-class-name"));
        props.put("url", environment.getProperty("olduat.datasource.url"));
        props.put("username", environment.getProperty("olduat.datasource.username"));
        props.put("password", environment.getProperty("olduat.datasource.password"));
        return DruidDataSourceFactory.createDataSource(props);
    }

    @Bean
    public DataSource frontNewDatasource() throws Exception {
        Properties props = new Properties();
        props.put("driverClassName", environment.getProperty("frontnew.datasource.driver-class-name"));
        props.put("url", environment.getProperty("frontnew.datasource.url"));
        props.put("username", environment.getProperty("frontnew.datasource.username"));
        props.put("password", environment.getProperty("frontnew.datasource.password"));
        return DruidDataSourceFactory.createDataSource(props);
    }

    @Bean
    public DataSource ytmatRookieDatasource() throws Exception {
        Properties props = new Properties();
        props.put("driverClassName", environment.getProperty("ytmat.datasource.driver-class-name"));
        props.put("url", environment.getProperty("ytmat.datasource.url"));
        props.put("username", environment.getProperty("ytmat.datasource.username"));
        props.put("password", environment.getProperty("ytmat.datasource.password"));
        return DruidDataSourceFactory.createDataSource(props);
    }

    /**
     * @Primary 该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错
     * @Qualifier 根据名称进行注入，通常是在具有相同的多个类型的实例的一个注入（例如有多个DataSource类型的实例）
     */
    @Bean
    @Primary
    public DynamicDataSource dataSource(@Qualifier("mysqlDatasource")DataSource mysqlDatasource,
                                        @Qualifier("uatstlDatasource")DataSource uatstlDatasource,
                                        @Qualifier("frontdbDatasource")DataSource frontdbDatasource,
                                        @Qualifier("olduatDatasource")DataSource olduatDatasource,
                                        @Qualifier("frontNewDatasource")DataSource frontNewDatasource,
                                        @Qualifier("ytmatRookieDatasource")DataSource ytmatRookieDatasource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DatabaseType.mysql, mysqlDatasource);
        targetDataSources.put(DatabaseType.UATSTL, uatstlDatasource);
        targetDataSources.put(DatabaseType.OLDUAT, olduatDatasource);
        targetDataSources.put(DatabaseType.FRONTDB, frontdbDatasource);
        targetDataSources.put(DatabaseType.FRONTNEW, frontNewDatasource);
        targetDataSources.put(DatabaseType.ROOKIE, ytmatRookieDatasource);

        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
        dataSource.setDefaultTargetDataSource(mysqlDatasource);// 默认的datasource设置为myTestDbDataSource

        return dataSource;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
        scannerConfigurer.setBasePackage("com.myproject.demo.dao");
        Properties props = new Properties();
        props.setProperty("mappers", "tk.mybatis.mapper.common.Mapper");
//        props.setProperty("IDENTITY", "MYSQL");
        props.setProperty("notEmpty", "true");
        scannerConfigurer.setProperties(props);
        return scannerConfigurer;
    }


    /**
     * 根据数据源创建SqlSessionFactory
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DynamicDataSource ds) throws Exception {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(ds);// 指定数据源(这个必须有，否则报错)
        // 下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
        fb.setTypeAliasesPackage("com.myproject.demo.entity");// 指定基包
        fb.setMapperLocations(resolver.getResources("classpath:mybatis/mapper/**/*.xml"));//
        return fb.getObject();
    }

    /**
     * 配置事务管理器
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DynamicDataSource dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }
}
