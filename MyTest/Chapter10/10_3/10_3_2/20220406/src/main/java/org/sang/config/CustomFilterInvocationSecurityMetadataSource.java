package org.sang.config;

import org.sang.mapper.MenuMapper;
import org.sang.model.Menu;
import org.sang.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    MenuMapper menuMapper;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException{
        String requestUrl = ((FilterInvocation) object).getRequestUrl();

        List<Menu> allMenus = menuMapper.getAllMenus();


        for(Menu menu : allMenus){
            if(antPathMatcher.match(menu.getPattern(), requestUrl)){
                List<Role> roles = menu.getRoles();
                String[] roleArr = new String[roles.size()];
                for(int i = 0; i < roleArr.length; i++){
                    roleArr[i] = roles.get(i).getName();
                }

                /**
                 * 返回访问页面所需要的    hasRole()
                 */
                return SecurityConfig.createList(roleArr);
            }
        }

        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes(){
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz){
        return FilterInvocation.class.isAssignableFrom(clazz);
    }


}
