package cn.xxc.cn.xxc.service.error;

import cn.xxc.cn.xxc.service.DemoService;
import org.springframework.stereotype.Service;

/**
 * created by xixincan
 * 2021/2/12
 */
@Service
public class DemoServiceError implements DemoService {

    @Override
    public String invoke() {
        return "系统维护中...";
    }
}
