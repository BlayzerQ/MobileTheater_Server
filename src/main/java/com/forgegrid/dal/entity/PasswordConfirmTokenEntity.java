package com.forgegrid.dal.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "password_confirm_tokens")
@Data
@NoArgsConstructor
public class PasswordConfirmTokenEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String token;
    @OneToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "id")
    private UserEntity user;
    private Date expirationDate;

    public PasswordConfirmTokenEntity(String token, UserEntity user, Date expirationDate) {
        this.token = token;
        this.user = user;
        this.expirationDate = expirationDate;
    }
}
