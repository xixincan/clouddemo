package cn.xxc.zpk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin2.server.internal.EnableZipkinServer;


/**
 * @author xixincan
 * 2020-07-28
 * @version 1.0.0
 */
@SpringBootApplication
// 声明启动ZipkinServer
@EnableZipkinServer
public class ZipkinApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZipkinApplication.class, args);
    }

}
