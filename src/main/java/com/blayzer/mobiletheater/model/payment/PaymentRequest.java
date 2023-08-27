package com.blayzer.mobiletheater.model.payment;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PaymentRequest {

    public String payment_token;
    public Amount amount;
    public boolean capture;
    public String description;

    @Data
    @ToString
    public static class Amount {
        private String value;
        private String currency;
    }
}
