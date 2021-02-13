package cn.xxc.service;

import cn.xxc.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * created by xixincan
 * 2021/2/13
 */
@FeignClient(name = "seata-account-service")
public interface AccountService {

    @PostMapping("account/decrease")
    CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);

}
