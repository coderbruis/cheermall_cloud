package com.cheermall.auth;

import com.cheermall.common.annotation.EnableCheerMallAuthExceptionHandler;
import com.cheermall.common.annotation.EnableCheerMallLettuceRedis;
import com.cheermall.common.annotation.EnableCheerMallServerProtect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @Author: LuoHaiYang
 */

@EnableDiscoveryClient
@EnableCheerMallLettuceRedis
@EnableCheerMallServerProtect
@EnableCheerMallAuthExceptionHandler
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MallAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallAuthApplication.class, args);
    }
}
