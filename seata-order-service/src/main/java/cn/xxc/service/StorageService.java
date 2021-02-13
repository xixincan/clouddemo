package cn.xxc.service;

import cn.xxc.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * created by xixincan
 * 2021/2/13
 */
@FeignClient(name = "seata-storage-service")
public interface StorageService {

    @PostMapping("/storage/decrease")
    CommonResult decease(@RequestParam("productId") Long productId, @RequestParam("count")  Integer count);

}
