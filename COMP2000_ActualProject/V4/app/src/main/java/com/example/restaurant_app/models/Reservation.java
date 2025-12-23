package com.example.restaurant_app.models;

public class Reservation {
    private int id;
    private String guestName;
    private String date;
    private String time;
    private String status;

    public Reservation(int id, String guestName, String date, String time, String status) {
        this.id = id;
        this.guestName = guestName;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    public Reservation(String guestName, String date, String time, String status) {
        this.guestName = guestName;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    public int getId() { return id; }
    public String getGuestName() { return guestName; }
    public String getDate() { return date; }
    public String getTime() { return time; }
    public String getStatus() { return status; }

    public void setStatus(String status) {
        this.status = status;
    }
}
