package cn.xxc.dao;

import cn.xxc.domain.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * created by xixincan
 * 2021/2/13
 */
public interface OrderDao {

    @Select("select * from t_order limit 10")
    List<Order> test();

    @Insert("insert into t_order (user_id, product_id, count, money, status)\n" +
            "        values(#{userId}, #{productId}, #{count}, #{money}, 0)")
    void createOrder(Order order);

    void endOrder(@Param("id") Long id, @Param("status") Integer status);
}
