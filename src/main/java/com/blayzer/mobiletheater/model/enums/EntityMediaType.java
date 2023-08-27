package com.blayzer.mobiletheater.model.enums;

public enum EntityMediaType {

    IMAGE ("IMAGE"),
    VIDEO ("VIDEO"),
    TEXT("TEXT"),
    DIALOG ("DIALOG");

    private String title;

    EntityMediaType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
