package cn.xxc.controller;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * created by xixincan
 * 2021/2/8
 */
@RestController
public class ServiceInstanceHandler {

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("services/instance/{name}")
    public List<ServiceInstance> serviceInstance(@PathVariable("name") String name) {
        if ("all".equals(name)) {
            List<String> nameList = this.discoveryClient.getServices();
            List<ServiceInstance> list = new ArrayList<>(nameList.size());
            for (String item : nameList) {
                list.addAll(this.discoveryClient.getInstances(item));
            }
            return list;
        }
        return this.discoveryClient.getInstances(name);
    }

}
