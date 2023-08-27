package com.blayzer.mobiletheater.model.payload;

import com.blayzer.mobiletheater.model.enums.EntityPayloadStatus;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class PayloadPromocode {
    public String user_uuid;
    public String promocode;
    public Integer spectacleId;
    public EntityPayloadStatus status;

    public PayloadPromocode(String user_uuid, String promocode, EntityPayloadStatus status) {
        this.user_uuid = user_uuid;
        this.promocode = promocode;
    }
}
