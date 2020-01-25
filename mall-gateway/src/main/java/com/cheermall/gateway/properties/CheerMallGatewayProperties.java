package com.cheermall.gateway.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author: LuoHaiYang
 */
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:mall-gateway.properties"})
@ConfigurationProperties(prefix = "mall.gateway")
public class CheerMallGatewayProperties {
    /**
     * 禁止外部访问的 URI，多个值的话以逗号分隔
     */
    private String forbidRequestUri;
}
