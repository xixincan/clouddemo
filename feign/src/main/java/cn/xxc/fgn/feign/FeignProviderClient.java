package cn.xxc.fgn.feign;

import cn.xxc.model.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

/**
 * @author xixincan
 * 2020-07-28
 * @version 1.0.0
 */
@FeignClient(value = "eureka-client-provider", fallback = FeignError.class)
public interface FeignProviderClient {

    @GetMapping("provider/find/all")
    Collection<Person> getAll();

}
