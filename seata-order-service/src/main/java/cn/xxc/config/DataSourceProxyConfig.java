package cn.xxc.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 不使用Spring Boot的自动装载数据源，主启动类中需要排除
 * 这里使用自己的数据源代理是为了是为了使用SEATA
 * 其实这里也不需要自己数据源代理了，yml中配置了自动代理，所以DataSourceProxy注释掉了
 * 这里自己到处是为了避免mybatis报错：缺少SqlSessionFactory或者SqlSessionTemplate
 *
 * created by xixincan
 * 2021/2/13
 */
@Configuration
public class DataSourceProxyConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return new DruidDataSource();
    }

//    配置了seata的自动代理，这里不需要手动代理了
//    @Bean
//    public DataSourceProxy dataSourceProxy(DataSource dataSource) {
//        return new DataSourceProxy(dataSource);
//    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSourceProxy) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSourceProxy);
        sqlSessionFactoryBean.setTransactionFactory(new SpringManagedTransactionFactory());
        return sqlSessionFactoryBean.getObject();
    }
}
