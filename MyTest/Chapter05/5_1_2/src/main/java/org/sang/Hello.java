//package org.sang;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class Hello {
//    @GetMapping ("/hello")
//    public String hello(){
//        return "Hello SpringBoot!";
//    }
//}



package org.sang;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {
    @GetMapping("/hello")
    public String hello(){
        return "Hello String";
    }
}
