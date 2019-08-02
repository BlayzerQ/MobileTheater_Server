package com.forgegrid.dal.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "logins_persistent")
@Data
@NoArgsConstructor
public class PersistentLoginEntity {
    @Id
    private String series;
    private String username;
    private String token;
    private Date lastUsed;
}
