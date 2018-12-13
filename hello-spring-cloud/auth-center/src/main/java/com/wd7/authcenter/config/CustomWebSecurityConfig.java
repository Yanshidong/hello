package com.wd7.authcenter.config;

import com.netflix.discovery.converters.Auto;
import com.wd7.authcenter.support.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
public class CustomWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyUserDetailsService userDetailsService;

    @Autowired
    CustomPasswordConfig passwordConfig;

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                过滤规则
                .authorizeRequests()
                .antMatchers("/api/say").permitAll()
                .antMatchers("/oauth/**").permitAll()
                .anyRequest().authenticated()
//                登录方式
                .and()
                .formLogin().permitAll()
                .and().httpBasic()
                .and().logout().clearAuthentication(true).deleteCookies("SESSION").permitAll()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)

                .and().headers().frameOptions().disable()
                .and().headers().cacheControl()

        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordConfig)
        ;

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers("/api/say");
        web.ignoring().mvcMatchers("/oauth/**");
        web.ignoring().antMatchers("/js/**");
        web.ignoring().antMatchers("/css/**");
        web.ignoring().antMatchers("/health");
        // 忽略登录界面
        web.ignoring().antMatchers("/login.html");
        web.ignoring().antMatchers("/index.html");
        web.ignoring().antMatchers("/oauth/user/token");
        web.ignoring().antMatchers("/oauth/client/token");
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }


    @Bean
    public RedisTokenStore redisTokenStore()
    {
        return new RedisTokenStore(redisConnectionFactory);
    }



}
