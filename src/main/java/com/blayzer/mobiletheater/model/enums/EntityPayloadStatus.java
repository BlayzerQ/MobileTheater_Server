package com.blayzer.mobiletheater.model.enums;

public enum EntityPayloadStatus {

    SUCCESS ("SUCCESS"),
    PENDING ("PENDING"),
    FAIL ("FAIL");

    private String title;

    EntityPayloadStatus(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
