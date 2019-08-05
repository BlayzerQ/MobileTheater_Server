package com.forgegrid.controllers;

import com.forgegrid.bussines.service.ResetPasswordService;
import com.forgegrid.bussines.service.UserService;
import com.forgegrid.presentation.dto.RequestPasswordResetForm;
import com.forgegrid.presentation.dto.ResetPasswordForm;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.UUID;

@Controller
public class ResetPasswordController {

    private static final String resetPasswordLinkPattern = "http://<host>:<port>/<endpoint>?token=<token>";
    private final UserService userService;
    private final ResetPasswordService resetPasswordService;

    public ResetPasswordController(UserService userService, ResetPasswordService resetPasswordService) {
        this.userService = userService;
        this.resetPasswordService = resetPasswordService;
    }

    @GetMapping("/resetpassword")
    public ModelAndView resetPassword(ModelAndView model) {
        model.addObject("requestPasswordResetForm", new RequestPasswordResetForm());
        return model;
    }

    @PostMapping("/resetpassword")
    public String resetPassword(@Valid @ModelAttribute("requestPasswordResetForm") RequestPasswordResetForm requestPasswordResetForm, Errors errors) {
        if (errors.hasErrors()) {
            return "resetpassword";
        }
        String email = requestPasswordResetForm.getEmail();
        if (!userService.isEmailRegistered(email) || resetPasswordService.hasToken(email)) {
            return "check_email";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        String token = UUID.randomUUID().toString();
        resetPasswordService.saveToken(token, email, calendar.getTime());
        String resetPasswordLink;
        try {
            resetPasswordLink = prepareResetLink(token);
        } catch (UnknownHostException e) {
            // TODO: Consider better error handling here
            e.printStackTrace();
            return "resetpassword";
        }
        try {
            resetPasswordService.sendResetPasswordMessage(
                    email,
                    "Password reset at ForgeGrid.com",
                    "noreply@forgegrid.com",
                    "To reset your password follow this link: " + resetPasswordLink
            );
        } catch (MailException mailException) {
            System.out.println("[DEBUG] Reset password link: " + resetPasswordLink);
            mailException.printStackTrace();
        }
        return "check_email";
    }

    private String prepareResetLink(String token) throws UnknownHostException {
        return resetPasswordLinkPattern
                .replace("<host>", "localhost")
                .replace("<port>", String.valueOf(8080))
                .replace("<endpoint>", "change-password")
                .replace("<token>", token);
    }

    @GetMapping("/change-password")
    public ModelAndView changePassword(ModelAndView model, @RequestParam("token") String token) {
        if (resetPasswordService.validatePasswordResetToken(token)) {
            model.addObject("resetPasswordForm", new ResetPasswordForm());
            model.addObject("token", token);
            model.setViewName("change_password");
        } else {
            model.setViewName("invalid_password_token");
        }
        return model;
    }

    @PostMapping("/change-password")
    public String changePassword(@Valid ResetPasswordForm resetPasswordForm) {
        String newPassword = resetPasswordForm.getNewPassword();
        resetPasswordService.updatePasswordByToken(newPassword, resetPasswordForm.getToken());
        return "redirect:/";
    }
}
