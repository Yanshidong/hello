package com.wd7.authcentercpn1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {


    @RequestMapping("/my/login")
    public String userLogin()
    {

        return "/user/index";
    }

    @RequestMapping(value = "my/logout",method = RequestMethod.GET)
    @ResponseBody
    public String userLogout()
    {
        return "redirect:/login";
    }


    @RequestMapping("/hello")
    public String hi()
    {
        return "hello";
    }
}
