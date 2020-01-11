package com.cheermall.gateway.runner;

import com.cheermall.gateway.MallGatewayApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author LuoHaiYang
 */
@Slf4j
@Component
public class StartedUpRunner {
    @Autowired
    private ConfigurableApplicationContext configurableApplicationContext;
}
