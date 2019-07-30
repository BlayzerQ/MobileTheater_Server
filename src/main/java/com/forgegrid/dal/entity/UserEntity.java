package com.forgegrid.dal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.Nullable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String login;
    @Column(unique = true)
    private String email;
    private String password;
    private Role role;
    private int money;

    public enum Role {
        ADMIN,
        DEVELOPER,
        USER,
        ANONYMOUS
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(Role.USER.name()));
    }

    @Override
    public String getUsername() {
        return this.login;
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

    public void alterLogin(@NotEmpty @Nullable String newLogin) {
        if (newLogin != null) {
            this.login = newLogin;
        }
    }

    public void alterEmail(@NotEmpty @Nullable String newEmail) {
        if (newEmail != null) {
            this.email = newEmail;
        }
    }

    public void alterPassword(@NotEmpty @Nullable String newPassword) {
        if (newPassword != null) {
            this.password = newPassword;
        }
    }
}
