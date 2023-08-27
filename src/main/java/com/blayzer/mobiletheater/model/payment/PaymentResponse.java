package com.blayzer.mobiletheater.model.payment;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PaymentResponse {
    private String id;
    private String status;
    private boolean paid;
    Amount AmountObject;
    Confirmation Confirmation;
    private String captured_at;
    private String created_at;
    private String description;
    Metadata MetadataObject;
    Payment_method Payment_methodObject;
    Recipient RecipientObject;
    private boolean refundable;
    Refunded_amount Refunded_amountObject;
    private boolean test;

    @Data
    @ToString
    public class Confirmation {
        private String type;
        private String confirmation_url;
    }

    @Data
    @ToString
    public class Refunded_amount {
        private String value;
        private String currency;
    }

    @Data
    @ToString
    public class Recipient {
        private String account_id;
        private String gateway_id;
    }

    @Data
    @ToString
    public class Payment_method {
        private String type;
        private String id;
        private boolean saved;
        Card CardObject;
        private String title;
    }

    @Data
    @ToString
    public class Card {
        private String first6;
        private String last4;
        private String expiry_month;
        private String expiry_year;
        private String card_type;
        private String issuer_country;
        private String issuer_name;
    }

    @Data
    @ToString
    public class Metadata { }
    public class Amount {
        private String value;
        private String currency;
    }
}

