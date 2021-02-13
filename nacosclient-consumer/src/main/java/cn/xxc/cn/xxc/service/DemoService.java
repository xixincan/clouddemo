package cn.xxc.cn.xxc.service;

import cn.xxc.cn.xxc.service.error.DemoServiceError;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * created by xixincan
 * 2021/2/12
 */
@FeignClient(name = "${my-nacos-provider-name}", fallback = DemoServiceError.class)
public interface DemoService {

    @GetMapping("nacos/provider/get")
    String invoke();

}
