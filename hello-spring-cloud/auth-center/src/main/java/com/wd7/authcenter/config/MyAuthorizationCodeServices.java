package com.wd7.authcenter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;

import java.util.concurrent.TimeUnit;

@Service
public class MyAuthorizationCodeServices extends RandomValueAuthorizationCodeServices{


    @Autowired
    RedisTemplate<Object,Object> redisTemplate;

//    @Autowired
//    CustomeConfigurationOAuth2Properties oAuth2Properties;
    Long timeOut=600L;
    String codePrefix="oauth2:code:";

    @Override
    protected void store(String code, OAuth2Authentication authentication) {

        //存储一个code
        redisTemplate.execute(new RedisCallback<Long>() {

            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(getCodeKey(code).getBytes(), SerializationUtils.serialize(authentication),
                        Expiration.from(timeOut, TimeUnit.SECONDS), RedisStringCommands.SetOption.UPSERT);
                return 1L;
            }
        });

    }

    @Override
    protected OAuth2Authentication remove(String code) {
        //消费一个code
        OAuth2Authentication oAuth2Authentication= redisTemplate.execute(new RedisCallback<OAuth2Authentication>() {

            @Override
            public OAuth2Authentication doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] keyByte = getCodeKey(code).getBytes();
                byte[] valueByte = connection.get(keyByte);

                if (valueByte != null) {
                connection.del(keyByte);
                    return (OAuth2Authentication) SerializationUtils.deserialize(valueByte);
                }

                return null;
            }
        });
        if(oAuth2Authentication==null)
        {
            return null;
        }
//        redisTemplate.delete(code);
        return oAuth2Authentication;
    }

    private String getCodeKey(String code)
    {
        return codePrefix+code;
    }
}
