package cn.xxc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xixincan
 * 2020-07-27
 * @version 1.0.0
 */
@RestController
public class ProviderHandler {

    @GetMapping("hello/{name}")
    public String sayHello(@PathVariable("name") String name) {
        return "Hello, ".concat(name).concat("!");
    }

}
