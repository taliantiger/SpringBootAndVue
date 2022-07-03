package org.talian.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/employee"})
public class EmployeeController {
    @RequestMapping(value={"/basic"})
    public String basic(){
        return "basic";
    }

    @RequestMapping(value={"/"})
    public String hello(){
        return "Hello";
    }

    /**
     * 自己特地创建个  /basic/hello,
     * 让他返回个basicHello
     *
     *
     * 注意：这里虽然 “/hello”  末尾没有  "/"符号，但实际访问的还是指代"/hello/" 目录，
     * 因为服务器中并没有名字为   hello   的文件;
     *
     * 但是用Postman 访问  "/basic/hello"  或”/basic/hello/“ 都能访问成功，
     * 猜测即使输入"basic/hello" ,  PostMan也会自动跳转到 "/basic/hello/" 目录，
     * 毕竟 "hello" 文件不存在，只存在 "/hello/"  目录
     * @return
     */
    @RequestMapping(value={"/basic/hello"})
    public String basicHello(){
        return "basicHello";
    }


//    @RequestMapping("/basic/hello")
//    public String hello(){
//        return "Hello";
//    }
}
