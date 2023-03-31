package com.bd.userusasmspanel.smspanel.Config;

import com.bd.userusasmspanel.smspanel.Entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomSmspanel implements UserDetails {
    private User smspanel;

    public CustomSmspanel(User smspanel) {
        this.smspanel = smspanel;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
     SimpleGrantedAuthority simpleGrantedAuthority= new SimpleGrantedAuthority(smspanel.getRole());
        return List.of(simpleGrantedAuthority);
    }

    @Override
    public String getPassword() {
        return smspanel.getPassword();
    }

    @Override
    public String getUsername() {
        return smspanel.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
