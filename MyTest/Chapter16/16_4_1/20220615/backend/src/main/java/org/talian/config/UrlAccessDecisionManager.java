package org.talian.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

/**
 * 确定完一个请求需要哪些角色后
 *
 * 在AccessDecisionManager 中进行角色信息对比
 *
 *
 */
@Component
public class UrlAccessDecisionManager implements AccessDecisionManager {

    /**
     * 重写decide方法，在该方法中，
     * 判断当前登录的用户是否具备当前请求URL所需要的角色信息，
     * 如果不具备，就抛出异常；
     * 否则不做任何事
     * @param auth  包含当前登陆用户的信息
     * @param o    一个FilterInvocation对象
     * @param cas  FilterInvocationSecurityMetadataSource 中的getAttributes方法的返回值，
     *             即当前请求URL所需要的角色
     */
    @Override
    public void decide(Authentication auth, Object o, Collection<ConfigAttribute> cas){
        Iterator<ConfigAttribute> iterator = cas.iterator();
        while(iterator.hasNext()){
            ConfigAttribute ca = iterator.next();
            String needRole = ca.getAttribute();

            if("ROLE_LOGIN".equals(needRole)){
                if(auth instanceof AnonymousAuthenticationToken){
                    throw new BadCredentialsException("未登录");
                }else
                    return;
            }


            // 当前用户所具有的权限
            Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
            for(GrantedAuthority authority : authorities){
                    if(authority.getAuthority().equals(needRole)){
                        return;
                    }
            }

            throw new AccessDeniedException("权限不足");
        }
    }



    @Override
    public boolean supports(ConfigAttribute configAttribute){
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass){
        return true;
    }

}
