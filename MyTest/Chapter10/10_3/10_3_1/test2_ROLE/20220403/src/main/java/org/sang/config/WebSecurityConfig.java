package org.sang.config;

import org.sang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
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



    /**
     * 角色继承
     *
     *
     *   ======》 实际继承失败，即使用root 访问 /db/hello, 也访问失败，Forbidden
     *   ======》 解决问题：数据库user添加上前缀ROLE，直接执行SQL，没有前缀ROLE,不符合书籍中的对应要求
     * @return
     */
    @Bean
    RoleHierarchy roleHierarchy(){
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy = "ROLE_dba > ROLE_admin   ROLE_admin> ROLE_user";
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
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

//                .antMatchers("/admin/**").hasRole("ROLE_admin")
//                .antMatchers("/db/**").hasRole("ROLE_dba")
//                .antMatchers("/user/**").hasRole("ROLE_user")

                .anyRequest().authenticated()


                .and()
                .formLogin()
                .loginProcessingUrl("/login").permitAll()
                .and()
                .csrf().disable();
    }

}
