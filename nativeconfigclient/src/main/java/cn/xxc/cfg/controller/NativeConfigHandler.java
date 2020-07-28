package cn.xxc.cfg.controller;

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
@RequestMapping("native")
public class NativeConfigHandler {

    @Value("${server.port}")
    private Integer port;

    @Value("${foo}")
    private String foo;

    @GetMapping("show")
    public String get() {
        return this.port + "; " + this.foo;
    }

}
