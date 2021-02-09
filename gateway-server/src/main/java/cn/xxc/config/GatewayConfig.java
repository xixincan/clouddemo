package cn.xxc.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Cloud Gateway 支持两种配置方式，一种是在yml中配置
 * 另一种就是JAVA config方式
 *
 * created by xixincan
 * 2021/2/9
 */
@Configuration
public class GatewayConfig {

    /**
     * 使用RouteLocatorBuilder构建RouteLocator并返回
     * route()表示一个路由规则，对应yml中的route，第一个参数对应yml中的id
     * 第二个参数配置 断言 以及 过滤器（过滤器可选）
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        // 请求news/**都转发到新闻网站http://news.baidu.com
//        routes.route("route_to_news", predicateSpec -> predicateSpec.path("/guoji").uri("http://news.baidu.com")).build();
        routes.route("route_to_news",
                predicateSpec -> predicateSpec.path("/news/**")
                        .filters(filterSpec -> filterSpec.stripPrefix(1))
                        .uri("http://news.baidu.com")
        ).build();
        return routes.build();
    }

}
