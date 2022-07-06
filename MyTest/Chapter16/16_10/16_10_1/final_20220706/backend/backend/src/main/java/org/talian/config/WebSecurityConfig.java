package org.talian.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.talian.bean.RespBean;
import org.talian.common.HrUtils;
//import org.talian.config.AuthenticationAccessDeniedHandler;
//import org.talian.config.UrlAccessDecisionManager;
import org.talian.service.HrService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    HrService hrService;

    @Autowired
    //CustomMetadataSource metadataSource;
    UrlFilterInvocationSecurityMetadataSource metadataSource;

    @Autowired
    UrlAccessDecisionManager urlAccessDecisionManager;

    @Autowired
    AuthenticationAccessDeniedHandler deniedHandler;


    //重写加密方法？
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(hrService).passwordEncoder(new BCryptPasswordEncoder());
    }

    //忽略该路径下的文件
    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/index.html", "/static/**", "/login_p");
    }

    //确认login页面，同时为可能出现的错误情况惊醒对应的返回提示配置
    @Override
    protected void configure(HttpSecurity http) throws Exception{

        /**
         * 前端开发中嵌入网页报错：Refused to display ‘http://localhost:8080/‘
         * in a frame because it set ‘X-Frame-Options‘
         * 添加以下代码防止报错
         *
         * https://www.jianshu.com/p/3c0f2910e2ed
         *
         *
         * ===> 20220705: 错误的实际原因，是再前端的index.js 中，少加了代码
         */
//        http.headers().frameOptions().sameOrigin();



        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>(){
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o){
                        o.setSecurityMetadataSource(metadataSource);
                        o.setAccessDecisionManager(urlAccessDecisionManager);
                        return o;
                    }
                })
                .and()
                .formLogin().loginPage("/login_p").loginProcessingUrl("/login")
                .usernameParameter("username").passwordParameter("password")
                .failureHandler(new AuthenticationFailureHandler(){
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req,
                                                        HttpServletResponse resp,
                                                        AuthenticationException e) throws IOException {
                        resp.setContentType("application/json;charset=utf-8");
                        RespBean respBean = null;
                        if(e instanceof BadCredentialsException || e instanceof UsernameNotFoundException) {
                            respBean = RespBean.error("账号或密码输入错误!");
                        }else if(e instanceof LockedException) {
                            respBean = RespBean.error("账户锁定，请联系管理员!");
                        }else if(e instanceof CredentialsExpiredException){
                            respBean = RespBean.error("密码过期，请联系管理员");
                        }else if(e instanceof AccountExpiredException) {
                            respBean = RespBean.error("账号过期，请联系管理员");
                        }else if(e instanceof DisabledException){
                            respBean = RespBean.error("账号禁用，请联系管理员");
                        }else{
                            respBean = RespBean.error("登录失败");
                        }

                        resp.setStatus(401);
                        ObjectMapper om = new ObjectMapper();
                        PrintWriter out = resp.getWriter();
                        out.write(om.writeValueAsString(respBean));
                        out.flush();
                        out.close();
                    }
                })

                //登录成功时对应的处理
                .successHandler(new AuthenticationSuccessHandler(){
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req,
                                                        HttpServletResponse resp,
                                                        Authentication auth) throws IOException{
                        resp.setContentType("application/json;charset=utf-8");
                        RespBean respBean = RespBean.ok("登录成功!", HrUtils.getCurrentHr());
                        ObjectMapper om = new ObjectMapper();
                        PrintWriter out = resp.getWriter();
                        out.write(om.writeValueAsString(respBean));
                        out.flush();
                        out.close();
                    }
                })
                .permitAll()
                .and()
                .logout().permitAll()
                .and().csrf().disable()
                .exceptionHandling().accessDeniedHandler(deniedHandler);
    }

}














//package org.talian.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.talian.common.HrUtils;
//import org.talian.service.HrService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.DisabledException;
//import org.springframework.security.config.annotation.ObjectPostProcessor;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
///**
// * Created by sang on 2017/12/28.
// */
//@Configuration
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    HrService hrService;
//
//    @Autowired
//    UrlFilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource;
//
//    @Autowired
//    UrlAccessDecisionManager urlAccessDecisionManager;
//
//    @Autowired
//    AuthenticationAccessDeniedHandler authenticationAccessDeniedHandler;
//
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(hrService).passwordEncoder(new BCryptPasswordEncoder());
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/index.html", "/static/**");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
//                    @Override
//                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
//                        o.setSecurityMetadataSource(urlFilterInvocationSecurityMetadataSource);
//                        o.setAccessDecisionManager(urlAccessDecisionManager);
//                        return o;
//                    }
//                }).and().formLogin().loginPage("/login_p").loginProcessingUrl("/login").usernameParameter("username").passwordParameter("password").permitAll().failureHandler(new AuthenticationFailureHandler() {
//            @Override
//            public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
//                                                HttpServletResponse httpServletResponse,
//                                                AuthenticationException e) throws IOException, ServletException {
//                httpServletResponse.setContentType("application/json;charset=utf-8");
//                PrintWriter out = httpServletResponse.getWriter();
//                StringBuffer sb = new StringBuffer();
//                sb.append("{\"status\":\"error\",\"msg\":\"");
//                if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
//                    sb.append("用户名或密码输入错误，登录失败!");
//                } else if (e instanceof DisabledException) {
//                    sb.append("账户被禁用，登录失败，请联系管理员!");
//                } else {
//                    sb.append("登录失败!");
//                }
//                sb.append("\"}");
//                out.write(sb.toString());
//                out.flush();
//                out.close();
//            }
//        }).successHandler(new AuthenticationSuccessHandler() {
//            @Override
//            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
//                httpServletResponse.setContentType("application/json;charset=utf-8");
//                PrintWriter out = httpServletResponse.getWriter();
//                ObjectMapper objectMapper = new ObjectMapper();
//                String s = "{\"status\":\"success\",\"msg\":" + objectMapper.writeValueAsString(HrUtils.getCurrentHr()) + "}";
//                out.write(s);
//                out.flush();
//                out.close();
//            }
//        }).and().logout().permitAll().and().csrf().disable().exceptionHandling().accessDeniedHandler(authenticationAccessDeniedHandler);
//    }
//}