package com.forgegrid.dal.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Type type;
    private String name;
    private String description;
    private String productVersion;
    private String mcVersion;
    private String changelog;
    private String developer;
    private int price;
    private Date lastUpdate = new Date();

    @PreUpdate
    protected void onUpdate() {
        lastUpdate = new Date();
    }

    enum Type {
        WEB,
        MOD,
        PLUGIN
    }
}
