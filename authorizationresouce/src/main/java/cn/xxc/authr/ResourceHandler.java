package cn.xxc.authr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xixincan
 * 2020-08-05
 * @version 1.0.0
 */
@RestController
public class ResourceHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceHandler.class);

    private static final String DEFAULT_NAME = "xuan";
    private static String DEFAULT_SERVICE_ID = "application";
    private static String DEFAULT_HOST = "localhost";
    private static String DEFAULT_PORT = "8080";

    // 受保护的资源（认证）
    @GetMapping(value = "/user/{userId}")
    public Map<String, String> getByUID(@PathVariable("userId") String userId) {
        LOGGER.info("Get User by UserId {}", userId);
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("userName", DEFAULT_NAME);
        return map;
    }

    // 不受保护的资源(无需认证)
    @GetMapping("/instance/{serviceId}")
    public Map<String, String> getInstance(@PathVariable("serviceId") String serviceId) {
        LOGGER.info("Get Instance by serviceId {}", serviceId);
        Map<String, String> map = new HashMap<>();
        map.put("serviceId", serviceId);
        map.put("host", DEFAULT_HOST);
        map.put("port", DEFAULT_PORT);
        return map;
    }


    private BearerTokenExtractor bearerTokenExtractor = new BearerTokenExtractor();

    @Autowired
    private ResourceServerTokenServices tokenServices;

    @RequestMapping("users")
    public Principal userInfo(HttpServletRequest request) {
        // 通过BearerTokenExtractor从请求中获取访问令牌
        Authentication authentication = this.bearerTokenExtractor.extract(request);
        String token = (String) authentication.getPrincipal();
        // 使用ResourceTokenServices加载访问令牌对应的OAuth2Authentication信息
        return this.tokenServices.loadAuthentication(token);
    }

    private static final ConcurrentHashMap<String,  CompletableFuture<Integer>> CAHCE = new ConcurrentHashMap<>(128);

    @GetMapping("aaa")
    public String a() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(20_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        });
        String key = String.valueOf(System.currentTimeMillis());
        CAHCE.put(key, future);
        return key;
    }

    @GetMapping("bbb")
    public Integer b(String key) {
        CompletableFuture<Integer> future = CAHCE.remove(key);
        if (null == future) {
            return  -1;
        }
        return future.join();
    }

}
