package com.wd7.authcentercpn1.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "wd7.oauth2")
public class CustomeConfigurationOAuth2Properties {

    private code code;

    @Data
    public static class code{
        private Integer timeout;
        private String prefix;
    }

}
