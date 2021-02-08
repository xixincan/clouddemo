package cn.xxc.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author xixincan
 * 2020-07-27
 * @version 1.0.0
 */
@RestController
@Configuration
public class ConsumerHandler {

    @Value("${spring.application.name}")
    private String name;
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("ask")
    public String ask() {
        String url = String.format("http://EUREKA-CLIENT-PROVIDER/hello/%s", this.name);
        return this.restTemplate.getForObject(url, String.class);
    }

    @Bean
    @LoadBalanced   // 注入一个可以进行负载均衡的RestTemplate用于服务调用
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
