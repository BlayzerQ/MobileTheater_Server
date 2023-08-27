package com.blayzer.mobiletheater.model.repository;

import com.blayzer.mobiletheater.model.entity.EntityPromocode;
import com.blayzer.mobiletheater.model.entity.EntitySpectacle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromocodeRepository extends JpaRepository<EntityPromocode, Integer> {

    EntityPromocode getEntityPromocodeByPromocode(String promocode);

}
