package com.blayzer.mobiletheater.model.enums;

public enum EntityPurchaseType {

    CARD ("CARD"),
    YMONEY ("YMONEY"),
    PROMOCODE ("PROMOCODE");

    private String title;

    EntityPurchaseType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
