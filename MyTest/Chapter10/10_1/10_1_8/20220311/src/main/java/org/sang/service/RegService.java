package org.sang.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 注册时对密码进行加密处理
 */
@Service
public class RegService{
    public int reg(String username, String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        String encodePassword = encoder.encode(password);
        return saveToDb(username, encodePassword);
    }

    private int saveToDb(String username, String encodePassword) {
        return 0;
    }
}