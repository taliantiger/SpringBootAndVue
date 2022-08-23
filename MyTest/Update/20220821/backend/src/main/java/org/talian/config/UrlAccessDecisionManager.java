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
     * @param collection  FilterInvocationSecurityMetadataSource 中的getAttributes方法的返回值，
     *             即当前请求URL所需要的角色
     */
    @Override
    public void decide(Authentication auth, Object o, Collection<ConfigAttribute> collection){
        Iterator<ConfigAttribute> iterator = collection.iterator();
        while(iterator.hasNext()){
            ConfigAttribute ca = iterator.next();

            //当前请求需要的权限
            String needRole = ca.getAttribute();
            /**
             * 如果需要的角色是ROLE_LOGIN,
             * 说明当前请求的URL用户登陆后即可访问
             */
            if("ROLE_LOGIN".equals(needRole)){
                if(auth instanceof AnonymousAuthenticationToken){
                    throw new BadCredentialsException("未登录");
                }else
                    return;
            }


            // 当前用户所具有的权限
            Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
            /**
             * 如果当前用户具备当前所需要的权限，那么方法结束
             */
            for(GrantedAuthority authority : authorities){
                    if(authority.getAuthority().equals(needRole)){
                        return;
                    }
            }
//            throw new AccessDeniedException("权限不足");
        }

        /**
         * 上面代码位置放错了，
         *
         * 写在了while 循环里面了，
         *
         * 过早抛出了 ”权限不足“ 的异常
         *
         * 导致无法对权限进行正确的判断。
         */
        throw new AccessDeniedException("权限不足");
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
