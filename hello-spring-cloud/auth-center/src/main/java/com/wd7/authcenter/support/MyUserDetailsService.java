package com.wd7.authcenter.support;


import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 授权的时候是对角色授权，而认证的时候应该基于资源，而不是角色，因为资源是不变的，而用户的角色是会变的
 */
@Service
public class MyUserDetailsService implements UserDetailsService {



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<SimpleGrantedAuthority> authorities=new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_SADMIN"));
        //权限
        authorities.add(new SimpleGrantedAuthority("userInfo:view"));
        authorities.add(new SimpleGrantedAuthority("userInfo:add"));
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        String pwd=bCryptPasswordEncoder.encode("a");
        return  new CustomUserDetails(username,pwd,true,true,true,true,authorities);



    }

}
