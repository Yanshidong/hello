package com.wd7.authcenter.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomPasswordConfig extends BCryptPasswordEncoder {
}
