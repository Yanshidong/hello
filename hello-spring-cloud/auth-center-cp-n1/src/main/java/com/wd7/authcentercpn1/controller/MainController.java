package com.wd7.authcentercpn1.controller;

import com.wd7.authcentercpn1.dao.SysUserRepository;
import com.wd7.authcentercpn1.defines.defines;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RestController
@RequestMapping("/index")
public class MainController {

    @Autowired
    ClientDetailsServiceConfigurer clientDetailsServiceConfigurer;

    @Autowired
    SysUserRepository sysUserRepository;


    @GetMapping("/sayHello")
    public String sayHello(String name) {
        return "Hello, " + name;
    }


    @PreAuthorize("hasRole('SADMIN')")
    @RequestMapping("/cSAdmin")
    public String checkSAdmin(Principal principal)
    {
        return "hi,this is super admin! "+principal.toString();
    }

    @PreAuthorize(value = "hasAuthority('"+ defines.AUTHORITY_USER_ADD+"')")
    @RequestMapping("/sayHi")
    public String sayHi(Authentication authentication) {

        return "hahaha,欢迎"+(authentication.getPrincipal().toString())+"来到此页;";
    }

    @RequestMapping("/userInfo")
    public Principal userInfo(Principal principal) {
        return principal;
    }

    @RequestMapping("/user/{id}")
    public String getUserById(@PathVariable Integer id)
    {
        return  sysUserRepository.getOne(id).toString();
    }


    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/login")
    public String userLogin()
    {
        return "/my/login";
    }





}
