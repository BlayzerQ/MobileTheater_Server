package com.blayzer.mobiletheater.model.entity;

import com.blayzer.mobiletheater.model.keys.EntityPurchaseKey;
import com.blayzer.mobiletheater.model.keys.EntityUserKey;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Data
@Entity
@NoArgsConstructor
@Table(name = "mt_purchases")
@IdClass(EntityPurchaseKey.class)
public class EntityPurchase {

    public EntityPurchase(Integer spectacleId, String userUid, String purchaseType) {
        this.spectacleId = spectacleId;
        this.userUid = userUid;
        this.purchaseType = purchaseType;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer id;
    @Id
    @Column(name = "spectacle_id")
    public Integer spectacleId;
    @Id
    @Column(name = "user_uuid")
    public String userUid;
    @Column(name = "purchase_type")
    public String purchaseType;
}
