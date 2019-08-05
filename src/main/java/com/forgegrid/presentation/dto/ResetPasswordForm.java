package com.forgegrid.presentation.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class ResetPasswordForm {
    @NotBlank(message = "Password is required!")
    @Size(min = 6, max = 64, message = "Password must be between 6 and 64 characters long!")
    private String newPassword;
    @NotBlank
    private String token;
}
