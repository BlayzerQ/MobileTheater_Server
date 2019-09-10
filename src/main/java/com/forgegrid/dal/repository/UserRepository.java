package com.forgegrid.dal.repository;

import com.forgegrid.dal.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> getByLogin(String login);

    Optional<UserEntity> getByEmail(String email);

    boolean existsByEmail(String email);
}
