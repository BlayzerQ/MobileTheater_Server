package com.blayzer.mobiletheater.model.payload;

import com.blayzer.mobiletheater.model.enums.EntityPayloadStatus;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class EntityAuth {
    public String phoneNumber;
    public String authToken;
    public String uuid;
    public EntityPayloadStatus status;

    public EntityAuth(String phoneNumber, String authToken, String uuid, EntityPayloadStatus status) {
        this.phoneNumber = phoneNumber;
        this.authToken = authToken;
        this.uuid = uuid;
        this.status = status;
    }
}
