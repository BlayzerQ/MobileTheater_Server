package com.forgegrid.bussines.service;

import com.forgegrid.dal.entity.UserEntity;
import com.forgegrid.presentation.dto.EditProfileForm;
import com.forgegrid.presentation.dto.RegistrationForm;

import javax.annotation.Nullable;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public interface UserService {

    void registerAndLogin(RegistrationForm registrationForm, HttpServletRequest request) throws ServletException;

    void updateWithFormAndPersist(EditProfileForm editProfileForm, UserEntity user);

    boolean isEmailRegistered(@Nullable String email);
}
