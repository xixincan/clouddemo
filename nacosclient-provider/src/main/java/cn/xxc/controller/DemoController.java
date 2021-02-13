package cn.xxc.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by xixincan
 * 2021/2/12
 */
@RestController
@RequestMapping("nacos/provider")
@RefreshScope
public class DemoController {

    @Value("${my-config-key:unknown}")
    private String configKey;

    @GetMapping("get")
    public String getKey() {
        System.out.println(this.configKey);
        return this.configKey;
    }

}
