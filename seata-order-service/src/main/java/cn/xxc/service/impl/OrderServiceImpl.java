package cn.xxc.service.impl;

import cn.xxc.dao.OrderDao;
import cn.xxc.domain.Order;
import cn.xxc.service.AccountService;
import cn.xxc.service.OrderService;
import cn.xxc.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * created by xixincan
 * 2021/2/13
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private AccountService accountService;
    @Resource
    private StorageService storageService;
    @Resource
    private OrderDao orderDao;

    @GlobalTransactional(name = "mytest", rollbackFor = Exception.class)
    @Override
    public void createOrder(Order order) {
        log.info("----->开始创建订单");
        this.orderDao.createOrder(order);
        log.info("创建订单完成");

        log.info("----》订单微服务开始调用库存，做扣减Count");
        this.storageService.decease(order.getProductId(), order.getCount());
        log.info("订单微服务调用库存扣减完成");

        log.info("--->>订单微服务调用账户服务扣钱Money");
        this.accountService.decrease(order.getUserId(), order.getMoney());
        log.info("订单微服务调用账户服务扣钱完成");

        log.info("====>>修改订单的状态");
        this.orderDao.endOrder(order.getId(), 1);
        log.info("下订单结束了O(∩_∩)O");
    }
}
