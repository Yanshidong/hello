package com.wd7.authcenter;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class RestTestController {

    @RequestMapping("/hi")
    public String hi()
    {
        return "hi baby.";
    }

    @RequestMapping("/say")
    public String say()
    {
        return "I would just like to say that this is the most hypocritical thing I have ever heard in my life";
    }

    @RequestMapping("/go")
    public String go()
    {
        return "You go first. I'll cover you.";
    }
}
