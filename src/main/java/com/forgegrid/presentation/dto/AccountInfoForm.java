package com.forgegrid.presentation.dto;

import com.forgegrid.dal.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountInfoForm {
    private String login;
    private String email;
    private UserEntity.Role role;
    private int money;
}
