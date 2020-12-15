package dao.entity;

import java.sql.Date;

public class ElectPrice {
    private Integer elect_price_gradient;
    private Date elect_price_update_date;
    private String admin_user;
    private Float elect_price_maximum;
    private String elect_price_dw;
    private Float elect_price_unit_price;

    public Integer getElect_price_gradient() {
        return elect_price_gradient;
    }

    public void setElect_price_gradient(Integer elect_price_gradient) {
        this.elect_price_gradient = elect_price_gradient;
    }

    public Date getElect_price_update_date() {
        return elect_price_update_date;
    }

    public void setElect_price_update_date(Date elect_price_update_date) {
        this.elect_price_update_date = elect_price_update_date;
    }

    public String getAdmin_user() {
        return admin_user;
    }

    public void setAdmin_user(String admin_user) {
        this.admin_user = admin_user;
    }

    public Float getElect_price_maximum() {
        return elect_price_maximum;
    }

    public void setElect_price_maximum(Float elect_price_maximum) {
        this.elect_price_maximum = elect_price_maximum;
    }

    public String getElect_price_dw() {
        return elect_price_dw;
    }

    public void setElect_price_dw(String elect_price_dw) {
        this.elect_price_dw = elect_price_dw;
    }

    public Float getElect_price_unit_price() {
        return elect_price_unit_price;
    }

    public void setElect_price_unit_price(Float elect_price_unit_price) {
        this.elect_price_unit_price = elect_price_unit_price;
    }
}
