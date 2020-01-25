package com.cheermall.auth.configure;

import com.cheermall.auth.properties.AuthProperties;
import com.cheermall.common.entity.constant.EndpointConstant;
import com.cheermall.common.handler.CheerMallAccessDeniedHandler;
import com.cheermall.common.handler.CheerMallAuthExceptionEntryPoint;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * 资源服务器配置
 *
 * @Author: LuoHaiYang
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfigure extends ResourceServerConfigurerAdapter {
    @Autowired
    private CheerMallAccessDeniedHandler cheerMallAccessDeniedHandler;

    @Autowired
    private CheerMallAuthExceptionEntryPoint cheerMallAuthExceptionEntryPoint;

    @Autowired
    private AuthProperties properties;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        String[] anonUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(properties.getAnonUrl(), ",");
        http.csrf().disable()
                .requestMatchers().antMatchers(EndpointConstant.ALL)
                .and()
                .authorizeRequests()
                .antMatchers(anonUrls).permitAll()
                .antMatchers(EndpointConstant.ALL).authenticated()
                .and().httpBasic();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.authenticationEntryPoint(cheerMallAuthExceptionEntryPoint)
                .accessDeniedHandler(cheerMallAccessDeniedHandler);
    }
}
