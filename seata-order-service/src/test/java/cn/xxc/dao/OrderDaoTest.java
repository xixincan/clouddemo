package cn.xxc.dao;

import cn.xxc.SeataOrderServiceApplication;
import cn.xxc.domain.Order;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * created by xixincan
 * 2021/2/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SeataOrderServiceApplication.class)
@Slf4j
public class OrderDaoTest {

    @Resource
    private OrderDao orderDao;

    @Test
    public void test() {
        log.info("开始测试order dao");
        List<Order> test = this.orderDao.test();
        log.info("test result:::{}", JSON.toJSON(test));
    }

}
