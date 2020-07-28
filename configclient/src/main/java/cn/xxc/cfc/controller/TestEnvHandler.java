package cn.xxc.cfc.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xixincan
 * 2020-07-28
 * @version 1.0.0
 */
@RestController
@RequestMapping("cfg")
public class TestEnvHandler {

    @Value("${env}")
    private String env;

    @GetMapping("env")
    public String env() {
        return env;
    }
}
