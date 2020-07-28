package cn.xxc.gtw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 不需要添加@SpringBootApplication
 * @EnableZuulProxy 包含了@
 *
 * @author xixincan
 * 2020-07-25
 * @version 1.0.0
 */
@EnableZuulProxy
@EnableAutoConfiguration
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}
