package cn.xxc.fgn.controller;

import cn.xxc.fgn.feign.FeignProviderClient;
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
@RequestMapping("feign")
public class FeignHandler {

    @Resource
    private FeignProviderClient feignProviderClient;

    @GetMapping("get/all")
    public Collection<Person> getAll() {
        return this.feignProviderClient.getAll();
    }

}
