//package org.sang.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
///**
// *  多个 HttpSecurity
// */
//@Configuration
//public class MultiHttpSecurityConfig{
//    @Bean
//    PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }
//
//    @Autowired
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth.inMemoryAuthentication()
//                .withUser("admin").password("123").roles("ADMIN", "USER")
//                .and()
//                .withUser("sang").password("123").roles("USER");
//    }
//
//    @Configuration
//    @Order(1)
//    public static class AdminSecurityConfig extends WebSecurityConfigurerAdapter{
//        @Override
//        protected void configure(HttpSecurity http) throws Exception{
//            http.antMatcher("/admin/**").authorizeRequests()
//                    .anyRequest().hasRole("ADMIN");
//        }
//    }
//
//    @Configuration
//    public static class OtherSecurityConfig extends WebSecurityConfigurerAdapter{
//        @Override
//        protected void configure(HttpSecurity http) throws Exception{
//            http.authorizeRequests()
//                    .anyRequest().authenticated()
//                    .and()
//                    .formLogin()
//                    .loginProcessingUrl("/login")
//                    .permitAll()
//                    .and()
//                    .csrf()
//                    .disable();
//        }
//    }
//}


package org.sang.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class MultiHttpSecurityConfig{
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }


    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("root").password("123").roles("ADMIN", "DBA")
                .and()



                .withUser("admin")

                /**
                 * 本案例是配置在内存中的用户===》无法直接用
                 *
                 * 存在数据库中，注册时加密，才能使用。
                 */
//                .password("$2a$10$RMuFXGQ5AtH4wOvkUqyvuecpqUSeoxZYqilXzbz50dceRsga.WYiq")

                .password("123")
                .roles("ADMIN", "USER")
                .and()



                .withUser("sang")
//                .password("$2a$10$eUHbAOMq4bpxTvOVz33LIehLe3fu6NwqC9tdOcxJXEhyZ4simqXTC")
                .password("123")
                .roles("USER");
    }

    @Configuration
    @Order(1)
    public static class AdminSecurityConfig extends WebSecurityConfigurerAdapter{
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    /**
                     * 没有：
                     *                     .and()
                     *                     .formLogin()
                     *                     .loginProcessingUrl("/login")
                     *                     .permitAll()
                     *
                     *  意味着不会自动跳转到  此时系统自带的 /login 逻辑中
                     */
                    .antMatcher("/admin/**").authorizeRequests()

                    .anyRequest().hasRole("ADMIN");
        }
    }

    @Order(90)
    @Configuration
    public static class OtherSecurityConfig extends WebSecurityConfigurerAdapter{
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginProcessingUrl("/login")
                    .permitAll()
                    .and()
                    .csrf()
                    .disable();
        }
    }
}
