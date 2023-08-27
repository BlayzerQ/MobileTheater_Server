package com.blayzer.mobiletheater.model.keys;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class EntityPromocodeKey implements Serializable {
    private Integer spectacleId;
    private String promocode;
}