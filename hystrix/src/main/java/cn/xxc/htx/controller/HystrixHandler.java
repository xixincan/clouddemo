package cn.xxc.htx.controller;

import cn.xxc.htx.feign.FeignProviderClient;
import cn.xxc.model.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * @author xixincan
 * 2020-07-28
 * @version 1.0.0
 */
@RestController
@RequestMapping("hystrix")
public class HystrixHandler {

    @Resource
    private FeignProviderClient feignProviderClient;

    @GetMapping("get/all")
    public Collection<Person> getAl() {
        return this.feignProviderClient.getAll();
    }

    @GetMapping("index")
    public String index() {
        return this.feignProviderClient.index();
    }

}
