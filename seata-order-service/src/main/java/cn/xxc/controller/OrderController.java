package cn.xxc.controller;

import cn.xxc.domain.CommonResult;
import cn.xxc.domain.Order;
import cn.xxc.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * created by xixincan
 * 2021/2/13
 */
@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("order/create")
    public CommonResult order(Order order) {
        this.orderService.createOrder(order);
        return CommonResult.success(null);
    }

}
