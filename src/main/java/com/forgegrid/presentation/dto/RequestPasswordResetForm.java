package com.forgegrid.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestPasswordResetForm {
    @NotBlank(message = "Email is required!")
    @Email(message = "Email must be valid")
    private String email;
}