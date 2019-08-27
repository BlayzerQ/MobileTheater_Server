package com.forgegrid.bussines.service;

import com.forgegrid.dal.entity.PasswordConfirmTokenEntity;
import com.forgegrid.dal.entity.UserEntity;
import com.forgegrid.dal.repository.PasswordConfirmTokenRepository;
import com.forgegrid.dal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResetPasswordService {
    private final PasswordConfirmTokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final JavaMailSender emailSender;
    private final PasswordEncoder passwordEncoder;

    public boolean hasToken(String email) {
        return tokenRepository.existsByUser_Email(email);
    }

    public void saveToken(String token, String email, Date expirationDate) {
        UserEntity user = userRepository.getByEmail(email);
        PasswordConfirmTokenEntity tokenEntity = new PasswordConfirmTokenEntity(token, user, expirationDate);
        tokenRepository.save(tokenEntity);
    }

    public void sendResetPasswordMessage(String destination, String subject, String from, String text) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(destination);
        mailMessage.setSubject(subject);
        mailMessage.setFrom(from);
        mailMessage.setText(text);
        emailSender.send(mailMessage);
    }

    public boolean validatePasswordResetToken(String token) {
        Optional<PasswordConfirmTokenEntity> possibleTokenEntity = tokenRepository.findByToken(token);
        if (!possibleTokenEntity.isPresent()) {
            return false;
        }
        PasswordConfirmTokenEntity tokenEntity = possibleTokenEntity.get();
        return !tokenEntity.getExpirationDate().before(Calendar.getInstance().getTime());
    }

    public void updatePasswordByToken(String newPassword, String token) {
        tokenRepository.findByToken(token).ifPresent(tokenEntity -> {
            UserEntity user = tokenEntity.getUser();
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            tokenRepository.deleteById(tokenEntity.getId());
        });
    }
}
