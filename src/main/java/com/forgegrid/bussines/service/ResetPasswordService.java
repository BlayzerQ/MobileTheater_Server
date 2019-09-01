package com.forgegrid.bussines.service;

import java.util.Date;

public interface ResetPasswordService {

    boolean hasToken(String email);

    void saveToken(String token, String email, Date expirationDate);

    void sendResetPasswordMessage(String destination, String subject, String from, String text);

    boolean validatePasswordResetToken(String token);

    void updatePasswordByToken(String newPassword, String token);
}
