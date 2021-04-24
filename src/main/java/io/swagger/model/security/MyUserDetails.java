package io.swagger.model.security;

import io.swagger.model.entity.UserCredentialsEntity;
import io.swagger.model.viewobjects.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {

    private final UserCredentialsEntity userCredentialsEntity;

    private final User.RoleEnum role;

    public MyUserDetails(UserCredentialsEntity userCredentialsEntity, User.RoleEnum role) {
        this.userCredentialsEntity = userCredentialsEntity;
        this.role = role;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(()-> role.toString());
    }

    @Override
    public String getPassword() {
        return userCredentialsEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userCredentialsEntity.getUsername();
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
        return userCredentialsEntity.getEnabled();
    }
}
