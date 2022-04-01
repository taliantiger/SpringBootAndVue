package org.sang.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 * 之前的页面访问前，认证授权，都是基于URL的：
 *          http.authorizeRequests()
 *                 .antMatchers("/admin/**")
 *                 .hasRole("ADMIN")
 *                 .antMatchers("/user/**")
 *                 .access("hasAnyRole('ADMIN', 'USER')")
 *                 .antMatchers("/db/**")
 *                 .access("hasRole('ADMIN') and hasRole('DBA')")
 *                 .anyRequest()
 *                 .authenticated()
 *                 .and()
 *                 .formLogin()
 *       意味着，只有ADMIN才能访问/admin/**,只有ADMIN或USER才能访问呢/user/**    .....
 *
 *
 *      现在可以通过  @EnableGlobalMethodSecurity 达到类似的目的
 *
 */

@Service
public class MethodService {
    @Secured("ROLE_ADMIN")
    public String admin(){
        return "hello admin";
    }

    @PreAuthorize("hasRole('ADMIN') and hasRole('DBA')")
    public String dba(){
        return "hello dba";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DBA', 'USER')")
    public String user(){
        return "hello user";
    }
}
