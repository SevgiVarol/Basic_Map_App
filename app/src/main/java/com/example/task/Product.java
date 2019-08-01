package com.example.task;

public class Product {
    private int id;
    private String event;
    private String detail;
    private String type;

    public Product(String event, String detail, String type) {
        this.id = id;
        this.event = event;
        this.detail = detail;
        this.type = type;
    }

    public int getId() {
        return this.id;
    }
    public String getEvent() {
        return this.event;
    }

    public String getDetail() {
        return this.detail;
    }
    public String getType() {
        return this.type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEvent(String name) {
        this.event = event;
    }

    public void setDetail(String name) {
        this.event = detail;
    }

    public void setType(String name) {
        this.event = type;
    }
}
