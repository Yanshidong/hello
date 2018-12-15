package com.wd7.authcentercpn1.config;

import com.netflix.discovery.converters.Auto;
import com.wd7.authcentercpn1.override.CustomRedisTokenStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

//@Configuration
//@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
//

    @Qualifier("CustomRedisTokenStore")
    @Autowired
    TokenStore tokenStore;

    @Autowired
    AuthenticationManager authenticationManager;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests().antMatchers("/index/**").authenticated()
            .antMatchers("/oauth/**","/login/**","/login**","/login").permitAll()

//        .anyRequest().authenticated()
//                .and()

//
        ;

    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        super.configure(resources);
//        resources.stateless
//        resources.stateless(true);

    resources.tokenStore(tokenStore).authenticationManager(authenticationManager)
        .stateless(true)

    ;
    }



}
