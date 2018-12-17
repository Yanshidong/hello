package com.wd7.authresourceserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * ResourceServerConfig override ResourceServerConfigerAdapter,
 * httpSecurityConfigure etc
 *
 * @Author: tutu
 * @Time: 上午9:31
 * @Date: 18-12-17
 * @www: http://www.7dwd7.com
 * @Email: wangde007@outlook.com
 * @CreateBy: idea
 * @Copyright (c) 2007-2018 7dwd7 Ltd. All Rights Reserved.
 **/
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{


}
