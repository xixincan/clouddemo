package cn.xxc.htx.feign;

import cn.xxc.model.Person;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 开启熔断机制后实现肺功能的接口，在出错后调用实现类中的实现方法
 * 接口注解中声明fallback = FeignError.class
 *
 * @author xixincan
 * 2020-07-28
 * @version 1.0.0
 */
@Component
public class FeignError implements FeignProviderClient {
    @Override
    public Collection<Person> getAll() {
        return null;
    }

    @Override
    public String index() {
        return "系统维护中...";
    }
}
