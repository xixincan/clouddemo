package cn.xxc.clt.controller;

import cn.xxc.model.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author xixincan
 * 2020-07-27
 * @version 1.0.0
 */
@RestController
@RequestMapping("provider")
public class ProviderHandler {

    @Value("${server.port}")
    private String port;

    @GetMapping("find/all")
    public Collection<Person> findAll() {
        return Arrays.asList(
                new Person("张三" + this.port, 12),
                new Person("李四" + this.port, 18),
                new Person("王武" + this.port, 22),
                new Person("赵六" + this.port, 26)
        );
    }

}
