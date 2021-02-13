package cn.xxc.cn.xxc.controller;

import cn.xxc.cn.xxc.service.DemoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * created by xixincan
 * 2021/2/12
 */
@RestController
@RequestMapping("nacos/consumer")
@RefreshScope
public class DemoController {

    @Value("${my-config-key:undefined}")
    private String configKey;
    @Value("${my-nacos-provider-name}")
    private String provider;

    @Resource
    private RestTemplate restTemplate;
    @Resource
    private DemoService demoService;

    @GetMapping("get")
    public String get() {
        System.out.println(this.configKey);
        return this.configKey;
    }

    @GetMapping("http")
    public String invoke() {
        String url = String.format("http://%s/nacos/provider/get", this.provider);
        return this.restTemplate.getForObject(url, String.class);
    }

    @GetMapping("feign")
    public String feignInvoke() {
        return this.demoService.invoke();
    }

}
