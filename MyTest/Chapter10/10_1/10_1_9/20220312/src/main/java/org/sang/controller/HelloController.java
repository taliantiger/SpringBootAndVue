package org.sang.controller;

import org.sang.service.MethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    MethodService methodService;

    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }

//    @GetMapping("/admin/hello")
//    public String admin(){
//        return "Hello admin!";
//    }

//    @GetMapping("/user/hello")
//    public String user(){
//        return "Hello user!";
//    }
//
//    @GetMapping("/db/hello")
//    public String dba(){
//        return "Hello dba!";
//    }

//    @GetMapping("/login_page")
//    public String login_page(){
//        return "login page!";
//    }


    /**
     * 通过把Service 注入到Controller中
     * 使得不同的admin、user、db附带了不同的Role权限
     * @return
     */
    @GetMapping("/admin/hello")
    public String admin(){
        return methodService.admin();
    }

    @GetMapping("/user/hello")
    public String user(){
        return methodService.user();
    }

    @GetMapping("/db/hello")
    public String dba(){
        return methodService.dba();
    }

}
