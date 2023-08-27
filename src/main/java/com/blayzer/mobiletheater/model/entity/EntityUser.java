package com.blayzer.mobiletheater.model.entity;

import com.blayzer.mobiletheater.model.keys.EntityUserKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Data
@NoArgsConstructor
@Entity
@Table(name = "mt_users")
@IdClass(EntityUserKey.class)
public class EntityUser {

    public EntityUser(String phone, String uuid) {
        this.phone = phone;
        this.uuid = uuid;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer id;
    @Id
    @Column(name = "phone")
    public String phone;
    @Id
    @Column(name = "uuid")
    public String uuid;
}
