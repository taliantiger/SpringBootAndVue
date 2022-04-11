package org.sang.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CustomAccessDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication auth,                 // 包含当前登录用户的信息

                       Object object,                       //  FilterInvocation对象，可以获取当前请求对象

                       Collection<ConfigAttribute> ca){    //访问页面所需要的    hasRole()

        Collection<? extends GrantedAuthority> auths = auth.getAuthorities();
        for(ConfigAttribute configAttribute : ca){
            if("ROLE_LOGIN".equals(configAttribute.getAttribute()) && auth instanceof UsernamePasswordAuthenticationToken) {
                return;
            }

            for(GrantedAuthority authority : auths){
                if(configAttribute.getAttribute().equals(authority.getAuthority())    ){
                    return;
                }
            }
        }

        throw new AccessDeniedException("权限不足");
    }



    @Override
    public boolean supports(ConfigAttribute attribute){
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz){
        return true;
    }
}
