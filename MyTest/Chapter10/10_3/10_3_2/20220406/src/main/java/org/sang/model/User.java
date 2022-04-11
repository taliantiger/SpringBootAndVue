package org.sang.model;


import org.sang.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class User implements UserDetails {
    private Integer id;
    private String username;
    private String password;
    private Boolean enabled;
    private Boolean locked;
    private List<Role> roles;

    public Collection<? extends GrantedAuthority> getAuthorities(){
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(Role role : roles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getUsername(){
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword(){
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * 这多要注释掉，否则提示 get .... enable... 报错
     *
     * org.springframework.security.authentication.InternalAuthenticationServiceException: nested exception is org.apache.ibatis.reflection.ReflectionException: Illegal overloaded getter method with ambiguous type for property enabled in class class org.sang.model.User. This breaks the JavaBeans specification and can cause unpredictable results.
     * 	at org.springframework.security.authentication.dao.DaoAuthenticationProvider.retrieveUser(DaoAuthenticationProvider.java:119) ~[spring-security-core-5.0.7.RELEASE.jar:5.0.7.RELEASE]
     * 	at org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider.authenticate(AbstractUserDetailsAuthenticationProvider.java:144) ~[spring-security-core-5.0.7.RELEASE.jar:5.0.7.RELEASE]
     * 	at org.springframework.security.authentication.ProviderManager.authenticate(ProviderManager.java:174) ~[spring-security-core-5.0.7.RELEASE.jar:5.0.7.RELEASE]
     * 	at org.springframework.security.authentication.ProviderManager.authenticate(ProviderManager.java:199) ~[spring-security-core-5.0.7.RELEASE.jar:5.0.7.RELEASE]
     * @return
     */
//    public Boolean getEnabled() {
//        return enabled;
//    }



    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isAccountNonLocked(){
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled(){
        return enabled;
    }


}
