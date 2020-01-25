package com.cheermall.common.configure;

import com.cheermall.common.handler.CheerMallAccessDeniedHandler;
import com.cheermall.common.handler.CheerMallAuthExceptionEntryPoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 *
 * 异常翻译配置
 *
 * @Author: LuoHaiYang
 */
public class CheerMallAuthExceptionConfigure {
    @Bean
    @ConditionalOnMissingBean(name = "accessDeniedHandler")
    public CheerMallAccessDeniedHandler cheerMallAccessDeniedHandler() {
        return new CheerMallAccessDeniedHandler();
    }

    @Bean
    @ConditionalOnMissingBean(name = "authenticationEntryPoint")
    public CheerMallAuthExceptionEntryPoint authExceptionEntryPoint() {
        return new CheerMallAuthExceptionEntryPoint();
    }
}
