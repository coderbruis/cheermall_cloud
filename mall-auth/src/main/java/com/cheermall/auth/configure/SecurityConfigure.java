package com.cheermall.auth.configure;

import com.cheermall.auth.filter.ValidateCodeFilter;
import com.cheermall.auth.service.impl.CheerMallUserDetailService;
import com.cheermall.common.entity.constant.EndpointConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Author: LuoHaiYang
 */
@Order(2)
@EnableWebSecurity
public class SecurityConfigure extends WebSecurityConfigurerAdapter {
    @Autowired
    private CheerMallUserDetailService userDetailService;

    @Autowired
    private ValidateCodeFilter validateCodeFilter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .requestMatchers()
                .antMatchers(EndpointConstant.OAUTH_ALL)
                .and()
                .authorizeRequests()
                .antMatchers(EndpointConstant.OAUTH_ALL).authenticated()
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
    }
}
