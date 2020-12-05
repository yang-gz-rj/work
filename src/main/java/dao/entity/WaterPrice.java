package dao.entity;

import java.sql.Date;

public class WaterPrice {
    private Integer water_price_gradient;
    private Date water_price_update_date;
    private String admin_user;
    private Float water_price_maximum;
    private String water_price_dw;
    private Float water_price_unit_price;

    public Integer getWater_price_gradient() {
        return water_price_gradient;
    }

    public void setWater_price_gradient(Integer water_price_gradient) {
        this.water_price_gradient = water_price_gradient;
    }

    public Date getWater_price_update_date() {
        return water_price_update_date;
    }

    public void setWater_price_update_date(Date water_price_update_date) {
        this.water_price_update_date = water_price_update_date;
    }

    public String getAdmin_user() {
        return admin_user;
    }

    public void setAdmin_user(String admin_user) {
        this.admin_user = admin_user;
    }

    public Float getWater_price_maximum() {
        return water_price_maximum;
    }

    public void setWater_price_maximum(Float water_price_maximum) {
        this.water_price_maximum = water_price_maximum;
    }

    public String getWater_price_dw() {
        return water_price_dw;
    }

    public void setWater_price_dw(String water_price_dw) {
        this.water_price_dw = water_price_dw;
    }

    public Float getWater_price_unit_price() {
        return water_price_unit_price;
    }

    public void setWater_price_unit_price(Float water_price_unit_price) {
        this.water_price_unit_price = water_price_unit_price;
    }
}
