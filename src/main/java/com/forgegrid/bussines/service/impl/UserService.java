package com.forgegrid.bussines.service.impl;

import com.forgegrid.dal.entity.UserEntity;
import com.forgegrid.presentation.dto.EditProfileForm;
import com.forgegrid.presentation.dto.RegistrationForm;
import com.forgegrid.dal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerAndLogin(RegistrationForm registrationForm, HttpServletRequest request) throws ServletException {
        String login = registrationForm.getLogin();
        String password = registrationForm.getPassword();
        UserEntity user = new UserEntity();
        user.setLogin(login);
        user.setEmail(registrationForm.getEmail());
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        request.login(login, password);
    }

    public void updateWithFormAndPersist(EditProfileForm editProfileForm, UserEntity user) {
        user.alterLogin(editProfileForm.getLogin());
        user.alterEmail(editProfileForm.getEmail());
        user.alterPassword(editProfileForm.getPassword());
        userRepository.save(user);
    }

    public boolean isEmailRegistered(@Nullable String email) {
        return email != null && userRepository.existsUserEntityByEmail(email);
    }
}
