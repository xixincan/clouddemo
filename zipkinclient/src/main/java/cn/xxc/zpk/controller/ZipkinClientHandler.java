package cn.xxc.zpk.controller;

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
@RequestMapping("zipkin")
public class ZipkinClientHandler {

    @Value("${server.port}")
    private Integer port;

    @GetMapping("port")
    public String port() {
        return "PORT:" + this.port;
    }

}
