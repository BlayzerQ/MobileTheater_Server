package com.blayzer.mobiletheater.model.payload;

import com.blayzer.mobiletheater.model.enums.EntityPayloadStatus;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class EntityPayment {
    public int spectacleId;
    public String user_uuid;
    public String paymentToken;
    public String paymentMethodType;
    public String confirmationUrl;
    public String paymentId;
    public EntityPayloadStatus status;

    public EntityPayment(int spectacleId, String user_uuid, String paymentToken, String paymentMethodType, EntityPayloadStatus status) {
        this.spectacleId = spectacleId;
        this.user_uuid = user_uuid;
        this.paymentToken = paymentToken;
        this.paymentMethodType = paymentMethodType;
        this.status = status;
    }
}
