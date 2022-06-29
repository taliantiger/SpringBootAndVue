package org.talian.config;

import org.talian.bean.Menu;
import org.talian.bean.Role;
import org.talian.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * 自定义FilterInvocationSecurityMetadataSource
 * 以实现动态配置权限
 * ====>> 确定一个请求需要那些权限
 */
@Component
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    MenuService menuService;

    //实现ant风格的URL匹配
    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //获取请求地址
        String requestUrl = ((FilterInvocation) o).getRequestUrl();

        //判断当前请求地址是否和  /login_p  请求地址相同，如果相同则取消过滤
        if ("/login_p".equals(requestUrl)) {
            return null;
        }

        //从数据库中获取所有资源信息，即menu表以及menu所对应的role
        List<Menu> allMenu = menuService.getAllMenu();

        for (Menu menu : allMenu) {
            if (antPathMatcher.match(menu.getUrl(), requestUrl) && menu.getRoles().size()>0) {
                List<Role> roles = menu.getRoles();
                int size = roles.size();
                String[] values = new String[size];
                for (int i = 0; i < size; i++) {
                    values[i] = roles.get(i).getName();
                }
                return SecurityConfig.createList(values);
            }
        }

        //如果没有匹配上的资源，则假设该请求登录后才可访问
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    //不需要Spring Security启动时校验相关配置是否正确，则返回null
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }


    //判断类对象是否支持校验
    //判断  FilterInvocation类对象  是否委派转换来自  aClass实例）????
    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
