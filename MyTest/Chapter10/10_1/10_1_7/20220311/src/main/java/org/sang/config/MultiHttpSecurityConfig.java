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
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class MultiHttpSecurityConfig{
    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                .withUser("root").password("123").roles("ADMIN", "DBA")
                .and()

                .withUser("admin").password("123").roles("ADMIN", "USER")
                .and()

                .withUser("sang").password("123").roles("USER");
    }

    @Configuration
    @Order(1)     // 此时，这个Configuration的优先级最高，无论如何，都会先执行————即Admin
    public static class AdminSecurityConfig extends WebSecurityConfigurerAdapter{
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/admin/**")


                    /**
                     * 没有：
                     *                     .and()
                     *                     .formLogin()
                     *                     .loginProcessingUrl("/login")
                     *                     .permitAll()
                     *
                     *  意味着不会自动跳转到  此时系统自带的 /login 逻辑中
                     */
                    .authorizeRequests().anyRequest().

                    hasRole("ADMIN");
        }
    }

    @Configuration
//    @Order(90)
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
