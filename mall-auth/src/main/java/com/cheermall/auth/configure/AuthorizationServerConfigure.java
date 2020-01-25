package com.cheermall.auth.configure;

import com.cheermall.auth.properties.AuthProperties;
import com.cheermall.auth.service.impl.CheerMallUserDetailService;
import com.cheermall.auth.service.impl.RedisClientDetailsServiceImpl;
import com.cheermall.auth.translator.MallWebResponseExceptionTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.util.UUID;

/**
 * @Author: LuoHaiYang
 *
 * 认证服务器配置
 *
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfigure extends AuthorizationServerConfigurerAdapter {

    /**
     * 认证授权Manger组件，密码模式需要用到的组件。
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 用户详情服务层
     */
    @Autowired
    private CheerMallUserDetailService userDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MallWebResponseExceptionTranslator   mallWebResponseExceptionTranslator;

    @Autowired
    private AuthProperties authProperties;

    @Autowired
    private RedisClientDetailsServiceImpl redisClientDetailsService;

    /**
     * Redis连接工厂
     */
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * ClientDetailsServiceConfigurer用来配置客户端详情服务，客户端详情信息
     * 要么通过硬编码方式，要么通过读取数据库或者缓存来获取。这里是通过redis缓存获取
     * 的。
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(redisClientDetailsService);
    }

    /**
     * AuthorizationServerSecurityConfigurer用来配置令牌断点(Token Endpoint)的
     * 安全约束。
     *
     * AuthorizationServerEndpointsConfigurer用来配置授权(
     *
     * @param endpoints
     */
    @Override
    @SuppressWarnings("unchecked")
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenStore(tokenStore())
                .userDetailsService(userDetailService)
                .authenticationManager(authenticationManager)
                .exceptionTranslator(mallWebResponseExceptionTranslator);
        if (authProperties.getEnableJwt()) {
            endpoints.accessTokenConverter(jwtAccessTokenConverter());
        }
    }

    /**
     * 令牌存储
     */
    @Bean
    public TokenStore tokenStore() {
        if (authProperties.getEnableJwt()) {
            return new JwtTokenStore(jwtAccessTokenConverter());
        } else {
            RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
            // 解决每次生成的token都一样的问题
            redisTokenStore.setAuthenticationKeyGenerator(oAuth2Authentication -> UUID.randomUUID().toString());
            return redisTokenStore;
        }
    }

    @Bean
    @Primary
    public DefaultTokenServices defaultTokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setClientDetailsService(redisClientDetailsService);
        return tokenServices;
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        DefaultAccessTokenConverter defaultAccessTokenConverter = (DefaultAccessTokenConverter)accessTokenConverter.getAccessTokenConverter();
        DefaultUserAuthenticationConverter userAuthenticationConverter = new DefaultUserAuthenticationConverter();
        userAuthenticationConverter.setUserDetailsService(userDetailService);
        defaultAccessTokenConverter.setUserTokenConverter(userAuthenticationConverter);
        accessTokenConverter.setSigningKey(authProperties.getJwtAccessKey());
        return accessTokenConverter;
    }

    /**
     *
     * ResourceOwnerPasswordTokenGranter是密码模式！
     * TokenGranter意思是：令牌授予者.
     *
     * oAuth2常见的授权模式有如下几种：
     * 1. AuthorizationCodeTokenGranter（授权码模式）
     * 2. ClientCredentialsTokenGranter（客户端模式）
     * 3. ImplicitTokenGranter（implicit模式）
     * 4. RefreshTokenGranter（刷新token模式）
     * 5. ResourceOwnerPasswordTokenGranter（密码模式）
     *
     * @param authenticationManager
     * @param oAuth2RequestFactory
     * @return
     */
    @Bean
    public ResourceOwnerPasswordTokenGranter resourceOwnerPasswordTokenGranter(AuthenticationManager authenticationManager, OAuth2RequestFactory oAuth2RequestFactory) {
        DefaultTokenServices defaultTokenServices = defaultTokenServices();
        if (authProperties.getEnableJwt()) {
            defaultTokenServices.setTokenEnhancer(jwtAccessTokenConverter());
        }
        return new ResourceOwnerPasswordTokenGranter(authenticationManager, defaultTokenServices, redisClientDetailsService, oAuth2RequestFactory);
    }

    /**
     *
     * DefaultOAuth2RequestFactory的createAuthorizationRequest会获取clientId、Secret、Scope、redirect_uri等重要信息
     * ，传入redisClientDetailsService，可以将redis缓存中存储的oauth_clients_details传入其中，构建oauth2_request请求。
     *
     * DefaultOAuth2RequestFactory是一个核心类！
     *
     * @return
     */
    @Bean
    public DefaultOAuth2RequestFactory oAuth2RequestFactory() {
        return new DefaultOAuth2RequestFactory(redisClientDetailsService);
    }
}
