package dao.entity;

import java.sql.Date;

public class Device {
    private String device_number;
    private String client_user;
    private String device_type;
    private Integer device_point;
    private String device_producer;
    private Float device_durability;
    private Date device_create_date;

    public String getDevice_number() {
        return device_number;
    }

    public void setDevice_number(String device_number) {
        this.device_number = device_number;
    }

    public String getClient_user() {
        return client_user;
    }

    public void setClient_user(String client_user) {
        this.client_user = client_user;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public Integer getDevice_point() {
        return device_point;
    }

    public void setDevice_point(Integer device_point) {
        this.device_point = device_point;
    }

    public String getDevice_producer() {
        return device_producer;
    }

    public void setDevice_producer(String device_producer) {
        this.device_producer = device_producer;
    }

    public Float getDevice_durability() {
        return device_durability;
    }

    public void setDevice_durability(Float device_durability) {
        this.device_durability = device_durability;
    }

    public Date getDevice_create_date() {
        return device_create_date;
    }

    public void setDevice_create_date(Date device_create_date) {
        this.device_create_date = device_create_date;
    }
}
