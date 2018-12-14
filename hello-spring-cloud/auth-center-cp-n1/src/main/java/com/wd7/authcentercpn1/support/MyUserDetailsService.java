package com.wd7.authcentercpn1.support;


import com.wd7.authcentercpn1.dao.SysUserRepository;
import com.wd7.authcentercpn1.details.CustomUserDetails;
import com.wd7.authcentercpn1.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    SysUserRepository sysUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        SysUser sysUser=sysUserService.findUserByUsername(username);
//        if(sysUser==null)throw new UsernameNotFoundException(username);
//        //查询指定username的用户拥有的所有角色和权限。
        List<SimpleGrantedAuthority> authorities=new ArrayList<>();
//        for(SysRole role:sysUser.getRoleList())
//        {
//            //load角色
//            authorities.add(new SimpleGrantedAuthority("ROLE_"+(role.getRole().toUpperCase())));
//            if(role.getSysPermissionList()==null)continue;
//            for(SysPermission permission:role.getSysPermissionList())
//            {
//                //load权限.
//                authorities.add(new SimpleGrantedAuthority(permission.getPermission()));
//            }
//        }

        //后续添加用户可用性的一些信息，那么判断用户可用性也是问题，其实用户需要一直判断可用性，并非一次登录就一直可用
//        return  new User(sysUser.getUsername(),sysUser.getPassword(),authorities);
        //使用user的完整构造
//        throw new SecurityException("系统临时更新");
        //角色
//        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        UserEntity userEntity=sysUserRepository.findByUsername(username);
        if(userEntity==null)throw new UsernameNotFoundException(username+" cannot find!");
        authorities.add(new SimpleGrantedAuthority("ROLE_SADMIN"));
        //权限
        authorities.add(new SimpleGrantedAuthority("userInfo:view"));
        authorities.add(new SimpleGrantedAuthority("userInfo:add"));
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        String pwd=bCryptPasswordEncoder.encode("a");
        //有必要去重写UserDetails，用来判断是否可用啊什么的,把这个对象处理一下,
        return  new CustomUserDetails(userEntity.getUsername(),pwd,true,true,true,true,authorities);



    }

}
