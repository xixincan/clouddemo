package cn.xxc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author xixincan
 * 2020-07-25
 * @version 1.0.0
 */
@SpringBootApplication
// 会自动配置必须的配置类，标识该服务为注册中心
@EnableEurekaServer
public class EurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }

}
