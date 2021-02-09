package cn.xxc.controller;

import cn.xxc.model.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;

/**
 * created by xixincan
 * 2021/2/9
 */
@RestController
@RequestMapping("provider")
public class FeignProviderHandler {

    @GetMapping("find/all")
    public Collection<Person> getAll() {
        Person person = new Person("test", 18);
        return Collections.singletonList(person);
    }

    @GetMapping("index")
    public String index() {
        return "welcome to index";
    }

}
