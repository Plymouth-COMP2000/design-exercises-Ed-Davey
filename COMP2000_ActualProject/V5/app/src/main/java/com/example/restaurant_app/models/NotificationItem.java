package com.example.restaurant_app.models;

public class NotificationItem {
    private int id;
    private String title;
    private String message;
    private String userType;
    private String timestamp;

    public NotificationItem(int id, String title, String message, String userType, String timestamp) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.userType = userType;
        this.timestamp = timestamp;
    }

    public NotificationItem(String title, String message, String userType) {
        this.title = title;
        this.message = message;
        this.userType = userType;
        this.timestamp = String.valueOf(System.currentTimeMillis());
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getMessage() { return message; }
    public String getUserType() { return userType; }
    public String getTimestamp() { return timestamp; }
}
