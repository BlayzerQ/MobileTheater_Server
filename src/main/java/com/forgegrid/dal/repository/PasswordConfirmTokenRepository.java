package com.forgegrid.dal.repository;

import com.forgegrid.dal.entity.PasswordConfirmTokenEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordConfirmTokenRepository extends CrudRepository<PasswordConfirmTokenEntity, Long> {
    Optional<PasswordConfirmTokenEntity> findByToken(String token);

    boolean existsByUser_Email(String email);
}
