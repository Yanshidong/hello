package com.wd7.authcenter.config;

import com.wd7.authcenter.support.MyClientDetailsService;
import com.wd7.authcenter.support.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;


//@Configuration
//@EnableAuthorizationServer
public class CustomAuthorizationServerConfigCopy extends AuthorizationServerConfigurerAdapter {

//    同步webSecurity
    @Autowired
    CustomPasswordConfig passwordConfig;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RedisTokenStore redisTokenStore;

    @Autowired
    MyClientDetailsService clientDetails;

    @Autowired
    MyUserDetailsService userDetailsService;


// 同步webSecurity 结束
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        super.configure(clients);
        clients.withClientDetails(clientDetails)
        ;

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        super.configure(endpoints);

        endpoints.authenticationManager(authenticationManager);
//        endpoints.userDetailsService()
        endpoints.tokenStore(redisTokenStore)
        .setClientDetailsService(clientDetails)
        ;
        endpoints.prefix("/")
                .userDetailsService(userDetailsService);



        ;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .passwordEncoder(passwordConfig)
                .allowFormAuthenticationForClients()

                ;
    }
}
