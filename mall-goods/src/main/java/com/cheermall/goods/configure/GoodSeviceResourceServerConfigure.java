package com.cheermall.goods.configure;

import com.cheermall.common.entity.constant.EndpointConstant;
import com.cheermall.common.handler.CheerMallAccessDeniedHandler;
import com.cheermall.common.handler.CheerMallAuthExceptionEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @Author: LuoHaiYang
 */
@Configuration
@EnableResourceServer
public class GoodSeviceResourceServerConfigure extends ResourceServerConfigurerAdapter {
    @Autowired
    private CheerMallAccessDeniedHandler cheerMallAccessDeniedHandler;
    @Autowired
    private CheerMallAuthExceptionEntryPoint exceptionEntryPoint;
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .requestMatchers().antMatchers(EndpointConstant.ALL)
                .and()
                .authorizeRequests()
                .antMatchers("/actuator/**").permitAll()
                .antMatchers(EndpointConstant.ALL).authenticated()
                .and()
                .httpBasic();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.authenticationEntryPoint(exceptionEntryPoint)
                .accessDeniedHandler(cheerMallAccessDeniedHandler);
    }
}
