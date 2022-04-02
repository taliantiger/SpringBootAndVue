package org.sang.config;

import org.sang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * 验证成功，登录界面可以直接通过明文123来登录
 * 数据库中存储的密码为加密文。
 */


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userService);
    }


    /**
     * 这里大部分配置和10.1节差不多，唯一不同是没有配置内存用户
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("admin")
                .antMatchers("/db/**").hasRole("dba")
                .antMatchers("/user/**").hasRole("user")
                .anyRequest().authenticated()


                .and()
                .formLogin()
                .loginProcessingUrl("/login").permitAll()
                .and()
                .csrf().disable();
    }
}
