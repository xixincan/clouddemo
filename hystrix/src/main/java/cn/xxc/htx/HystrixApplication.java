package cn.xxc.htx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author xixincan
 * 2020-07-28
 * @version 1.0.0
 */
@SpringBootApplication
@EnableFeignClients
// 声明启动数据监控
@EnableCircuitBreaker
// 声明启用可视化的数据监控（仪表盘）
@EnableHystrixDashboard
public class HystrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixApplication.class, args);
    }

}
