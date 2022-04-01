package org.sang.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }

    @GetMapping("/admin/hello")
    public String admin(){
        return "Hello admin!";
    }

    @GetMapping("/user/hello")
    public String user(){
        return "Hello user!";
    }

    @GetMapping("/db/hello")
    public String dba(){
        return "Hello dba!";
    }

//    @GetMapping("/login_page")
//    public String login_page(){
//        return "login page!";
//    }
}
