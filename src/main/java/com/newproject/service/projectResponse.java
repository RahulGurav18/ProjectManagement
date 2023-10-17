package com.newproject.service;

public class projectResponse {
    private String message;

    public projectResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
