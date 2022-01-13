package myproject.controller;

import myproject.Author;
import myproject.Book;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


//添加全局数据
@ControllerAdvice
public class GlobalConfig {
    @ModelAttribute(value = "info")
    public Map<String, String> userInfo(){
        HashMap<String, String> map = new HashMap<>();
//        map.put("username", "LuoGuanZhong");
//        map.put("gender", "male");

        map.put("username", "罗贯中");
        map.put("gender", "男");
        return map;
    }
}
