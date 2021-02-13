package cn.xxc.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * created by xixincan
 * 2021/2/13
 */
@Configuration
@MapperScan(basePackages = {"cn.xxc.dao"})
public class MyBatisConfig {
}
