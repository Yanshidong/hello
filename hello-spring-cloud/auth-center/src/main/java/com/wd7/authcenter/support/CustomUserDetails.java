package com.wd7.authcenter.support;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails  implements UserDetails, CredentialsContainer {

    private    String  username;
    private    String  password;
    private    boolean accountNonExpired;
    private    boolean accountNonLocked;
    private    boolean credentialsNonExpired;
    private    boolean enabled;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(String username, String password, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled, Collection<? extends GrantedAuthority> authorities) {
        setUsername(username);
        setPassword(password);
        setAccountNonExpired(accountNonExpired);
        setAccountNonLocked(accountNonLocked);
        setCredentialsNonExpired(credentialsNonExpired);
        setEnabled(enabled);
        setAuthorities(authorities);

    }

    @Override
    public void eraseCredentials() {
        setPassword(null);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        //在这里添加自己的可用性判断逻辑
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        //在这里添加自己的可用性判断逻辑
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        //在这里添加自己的可用性判断逻辑
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        //在这里添加自己的可用性判断逻辑
        return enabled;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
