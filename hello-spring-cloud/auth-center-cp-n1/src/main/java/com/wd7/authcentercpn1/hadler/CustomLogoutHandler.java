package com.wd7.authcentercpn1.hadler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CustomLogoutHandler implements LogoutHandler {

    final
    TokenStore tokenStore;


    private final RedisConnectionFactory connectionFactory;

    @Autowired
    public CustomLogoutHandler(RedisConnectionFactory connectionFactory, @Qualifier("CustomRedisTokenStore") TokenStore tokenStore) {
        this.connectionFactory = connectionFactory;
        this.tokenStore = tokenStore;
    }

    /**
     * Causes a logout to be completed. The method must complete successfully.
     *
     * @param request        the HTTP request
     * @param response       the HTTP response
     * @param authentication the current principal details
     */
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        authentication.getCredentials();
        //如果在这里注入tokenStore会怎样,注入失败,null get。这里确实执行了，那么可以在这里清除redisTokenStore的token信息！！
        System.out.println("CustomLogoutHandler!!");
        String token=this.getToken(request);
//        TokenStore tokenStore=;

          OAuth2AccessToken accesstoken =tokenStore.readAccessToken(token);
          OAuth2RefreshToken refreshToken=tokenStore.readRefreshToken(token);

          if(accesstoken!=null)tokenStore.removeAccessToken(accesstoken);
          if(refreshToken!=null)tokenStore.removeRefreshToken(refreshToken);
    }


    private String getToken(HttpServletRequest request)
    {
        String token=null;
        String HeaderToken=request.getHeader("Authorization");
        //header token
        if(HeaderToken!=null)
        {
            //从header里截取token，
            if(HeaderToken.contains("Bearer")){
                token=HeaderToken.substring(7);
            }
        }

        //parameter token

        if(token==null&&request.getParameter("access_token")!=null)
        {
            token=request.getParameter("access_token");
        }

        return token;
    }
}
