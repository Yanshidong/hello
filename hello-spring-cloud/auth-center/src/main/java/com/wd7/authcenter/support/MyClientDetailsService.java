package com.wd7.authcenter.support;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MyClientDetailsService implements ClientDetailsService {
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        CustomClientDetails clientDetails=new CustomClientDetails();
        Set<String> resourceIds=new HashSet<String>();
        Set<String> scopes=new HashSet<String>();
        Set<String> authorizationGrantTypes=new HashSet<String>();
        Set<String> registeredRedirectUri=new HashSet<String>();
        Collection<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
        Map<String,Object> additionalInfomation=new HashMap<String,Object>();

        resourceIds.add("oauth2-resource");
        scopes.add("userInfo:view");
        scopes.add("app");
//        scopes.add("userInfo:add");
        authorizationGrantTypes.add("password");
        authorizationGrantTypes.add("authorization_code");
        authorizationGrantTypes.add("refresh_token");
        authorizationGrantTypes.add("client_credentials");
        //authorization_code,password,refresh_token,client_credentials

        registeredRedirectUri.add("https://www.getpostman.com/oauth2/callback");
        registeredRedirectUri.add("http://192.168.1.131:8901/login");
        registeredRedirectUri.add("http://localhost:8901/login");
        registeredRedirectUri.add("http://127.0.0.1:8901/login");
        registeredRedirectUri.add("http://192.168.1.105:8901/login");
        registeredRedirectUri.add("http://192.168.1.130:8770/login");
        registeredRedirectUri.add("http://192.168.1.130:8770/customLogin");
        registeredRedirectUri.add("http://localhost:8770/callback");
        registeredRedirectUri.add("http://192.168.1.131:9997/clientOne/login");
        registeredRedirectUri.add("http://192.168.1.131:9996/clientOne/login");
//        registeredRedirectUri.add("http://192.168.1.131:9996/clientOne/login");

        registeredRedirectUri.add("http://192.168.1.130:9997/clientOne/login");
        registeredRedirectUri.add("http://127.0.0.1:9997/clientOne/login");
        registeredRedirectUri.add("http://localhost:9997/clientOne/login");
//        authorities.add("");
        additionalInfomation.put("name","这里是测试client");
        BCryptPasswordEncoder coder=new BCryptPasswordEncoder();
        clientDetails.setClientId(clientId);
        clientDetails.setResourceIds(resourceIds);//secret
        clientDetails.setClientSecret(coder.encode("owen"));
        clientDetails.setScope(scopes);
        clientDetails.setAuthorizedGrantTypes(authorizationGrantTypes);
        clientDetails.setRegisteredRedirectUri(registeredRedirectUri);
        clientDetails.setAuthorities(authorities);
        clientDetails.setAccessTokenValiditySeconds(36000);
        clientDetails.setRefreshTokenValiditySeconds(36000);
        clientDetails.setAdditionalInformation(additionalInfomation);
        clientDetails.setAutoApprove(true);


     return clientDetails;
    }
}
