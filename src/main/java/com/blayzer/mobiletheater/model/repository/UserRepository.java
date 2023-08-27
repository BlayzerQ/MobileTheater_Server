package com.blayzer.mobiletheater.model.repository;

import com.blayzer.mobiletheater.model.entity.EntityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<EntityUser, Integer> {

}
