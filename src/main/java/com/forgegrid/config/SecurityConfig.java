package com.forgegrid.config;

import com.forgegrid.authentication.CachedRememberMeServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import java.util.UUID;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final PersistentTokenRepository persistentTokenRepository;

    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(
                        "/",
                        "/pages/**",
                        "/catalog/**",
                        "/news/**",
                        "/tasks/**",
                        "/about",
                        "/registration",
                        "/resetpassword",
                        "/change-password",
                        "/webjars/**",
                        "/rest/news",
                        "/rest/news/*",
                        "/rest/file/upload"
                ).permitAll()
                .anyRequest().authenticated();
        http
                .formLogin()
                .loginPage("/")
                .loginProcessingUrl("/processLogin")
                .failureHandler((request, response, authentication) -> {
                    response.getWriter().append("Incorrect login or password!");
                    response.setStatus(403);
                });
        http
                .logout()
                .logoutSuccessUrl("/");
        String tokenKey = UUID.randomUUID().toString();
        http
                .rememberMe()
                .key(tokenKey)
                .tokenRepository(persistentTokenRepository)
                .rememberMeServices(new CachedRememberMeServices(
                        tokenKey,
                        userDetailsService,
                        persistentTokenRepository
                ));
    }
}
