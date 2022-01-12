package myproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 要么将@controller改为@RestController，
 * 要么在@controller下面添加@ResponseBody。
 * ResponseBody注释将方法返回值绑定到web响应主体，因此它是必需的。
 */
//@ControllerAdvice


//@Controller
//@ResponseBody
@RestController
public class HelloController {
    @GetMapping("hello")
    @ResponseBody
    public void hello(Model model) {
        Map<String, Object> map = model.asMap();
        Set<String> keySet = map.keySet();
        Iterator<String> iterator = keySet.iterator();
        while(iterator.hasNext()){
            String key = iterator.next();
            Object value = map.get(key);
            System.out.println(key + ">>>>>" + value);
        }
    }
}