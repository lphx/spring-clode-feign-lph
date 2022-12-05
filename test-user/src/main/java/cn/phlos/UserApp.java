package cn.phlos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName: UserApp
 * @Author: lph
 * @Description:
 * @Date: 2022/12/5 22:21
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients //扫描和注册feign客户端bean定义
public class UserApp {

    public static void main(String[] args) {
        SpringApplication.run(UserApp.class, args);
    }

}
