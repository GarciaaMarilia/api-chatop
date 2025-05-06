package com.openclassromms.api.security;

import com.openclassromms.api.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Retorna as permissões do usuário (no seu caso, poderia ser uma lista de roles ou algo similar)
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return user.getPassword(); // A senha encriptada do usuário
    }

    @Override
    public String getUsername() {
        return user.getEmail(); // Ou o campo de login, se diferente
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Caso haja algum controle de expiração de conta
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Caso haja algum controle de bloqueio de conta
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Caso haja algum controle de expiração de credenciais
    }

    @Override
    public boolean isEnabled() {
        return true; // Caso haja algum controle de status de conta (ativa/desativada)
    }

    // Getter para acessar o objeto User original
    public User getUser() {
        return user;
    }
}
