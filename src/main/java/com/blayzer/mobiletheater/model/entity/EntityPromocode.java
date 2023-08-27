package com.blayzer.mobiletheater.model.entity;

import com.blayzer.mobiletheater.model.keys.EntityPromocodeKey;
import com.blayzer.mobiletheater.model.keys.EntityPurchaseKey;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Data
@Entity
@NoArgsConstructor
@Table(name = "mt_promocodes")
@IdClass(EntityPromocodeKey.class)
public class EntityPromocode {

    public EntityPromocode(Integer spectacleId, String userUid, String promocode) {
        this.spectacleId = spectacleId;
        this.userUid = userUid;
        this.promocode = promocode;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer id;
    @Id
    @Column(name = "spectacle_id")
    public Integer spectacleId;
    @Column(name = "user_uuid")
    public String userUid;
    @Id
    @Column(name = "promocode")
    public String promocode;
}
