package cn.xxc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author xixincan
 * 2020-08-07
 * @version 1.0.0
 */
@RestController
public class MainHandler {

    @GetMapping("")
    public String main() {
        return "Welcome to the index...";
    }

    @GetMapping("/index")
    public String index() {
        return "Welcome to the index...";
    }

    @GetMapping("user")
    public Principal principal(Principal user) {
        return user;
    }

}
