package cn.xxc.rbon.controller;

import cn.xxc.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

/**
 * @author xixincan
 * 2020-07-27
 * @version 1.0.0
 */
@RestController
@RequestMapping("ribbon")
public class RibbonHandler {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("find/all")
    public Collection<Person> findAll() {
        // 使用Ribbon时，使用注册的服务名称来代替名称
        return this.restTemplate.getForObject("http://provider1/provider/find/all", Collection.class);
    }

}
