package com.wd7.authcenter.config;

import com.wd7.authcenter.support.MyClientDetailsService;
import com.wd7.authcenter.support.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2RequestValidator;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestValidator;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;
import javax.sql.DataSource;


//@Configuration
//@EnableAuthorizationServer
public class CustomAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private RedisConnectionFactory connectionFactory;

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Autowired
    private MyClientDetailsService myClientDetailsService;


    @Autowired
    AuthenticationManager authenticationManager;

    @Resource
    private DataSource dataSource;

    @Autowired
    private MyUserDetailsService userDetailsService;

//    @Autowired
//    private MyAuthorizationCodeServices myAuthorizationCodeServices;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients()
                .passwordEncoder(new BCryptPasswordEncoder())
        ;

    }
    //配置clientDetails 存储位置
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.withClientDetails(myClientDetailsService)
        ;
    }

    //配置token存储位置
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        JdbcAuthorizationCodeServices jdbcAuthorizationCodeServices=new JdbcAuthorizationCodeServices(dataSource);

        TokenEnhancerChain  tokenEnhancerChain=new TokenEnhancerChain();

        OAuth2RequestValidator oAuth2RequestValidator=new DefaultOAuth2RequestValidator();

        //设置token存储
        endpoints.tokenStore(tokenStore()) //redis


//        .setClientDetailsService(myClientDetailsService);

                .approvalStore(new JdbcApprovalStore(dataSource)) //jdbc,每次认证后会更新一次用户关于这个client的认证记录,这个还是写数据库比较好

                .authenticationManager(authenticationManager) //默认

        .userDetailsService(userDetailsService) //jdbc,慢了之后可以直接从这里迁移到redis或其他高速数据库,查询用户信息

//                .authorizationCodeServices(myAuthorizationCodeServices) //redis,这东西读写 code,

                .tokenEnhancer(tokenEnhancerChain)

                .requestValidator(oAuth2RequestValidator)

                .tokenServices(defaultTokenServices())







//        endpoints.approvalStoreDisabled();
//        endpoints

//                .tokenEnhancer(accessTokenConverter())
//                .tokenStore(tokenStore())
        ;


    }

    @Bean(name = "CustomRedisTokenStore")
    public TokenStore tokenStore() {

        RedisTokenStore redisTokenStore= new RedisTokenStore(connectionFactory);
//        redisTokenStore.setAuthenticationKeyGenerator(new CustomRandomKeyGenerator());
        return redisTokenStore;
    }

    @Primary
    @Bean
    public DefaultTokenServices defaultTokenServices()
    {
        DefaultTokenServices tokenServices=new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setAccessTokenValiditySeconds(60*60*12);
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setRefreshTokenValiditySeconds(86400*7);
        return tokenServices;
    }
}
