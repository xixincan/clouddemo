package cn.xxc.con.controller;

import cn.xxc.model.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * @author xixincan
 * 2020-07-27
 * @version 1.0.0
 */
@RestController
@RequestMapping("consumer")
public class ConsumerHandler {

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("find/all")
    public Collection<Person> getAll() {
        return this.restTemplate.getForObject("http://localhost:8010/provider/find/all", Collection.class);
    }
}
