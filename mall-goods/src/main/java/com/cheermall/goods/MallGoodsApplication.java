package com.cheermall.goods;

import com.cheermall.common.annotation.CheerMallCloudApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @Author: LuoHaiYang
 */
@EnableFeignClients
@CheerMallCloudApplication
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MallGoodsApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallGoodsApplication.class, args);
    }
}
