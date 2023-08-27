package com.blayzer.mobiletheater.model.repository;

import com.blayzer.mobiletheater.model.entity.EntityPurchase;
import com.blayzer.mobiletheater.model.entity.EntityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<EntityPurchase, Integer> {

    List<EntityPurchase> getAllByUserUid(String uid);
}
