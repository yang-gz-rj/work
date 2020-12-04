package dao.entity;

import java.sql.Date;

public class WaterBill {

    private String water_bill_number;
    private String device_number;
    private Integer water_price_gradient;
    private Date water_price_update_date;
    private Float water_bill_init_value;
    private Float water_bill_now_value;
    private String water_bill_r_dw;
    private Date water_bill_output_date;
    private Float water_bill_fee;
    private Date water_bill_pay_date;

    public String getWater_bill_number() {
        return water_bill_number;
    }

    public void setWater_bill_number(String water_bill_number) {
        this.water_bill_number = water_bill_number;
    }

    public String getDevice_number() {
        return device_number;
    }

    public void setDevice_number(String device_number) {
        this.device_number = device_number;
    }

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

    public Float getWater_bill_init_value() {
        return water_bill_init_value;
    }

    public void setWater_bill_init_value(Float water_bill_init_value) {
        this.water_bill_init_value = water_bill_init_value;
    }

    public Float getWater_bill_now_value() {
        return water_bill_now_value;
    }

    public void setWater_bill_now_value(Float water_bill_now_value) {
        this.water_bill_now_value = water_bill_now_value;
    }

    public String getWater_bill_r_dw() {
        return water_bill_r_dw;
    }

    public void setWater_bill_r_dw(String water_bill_r_dw) {
        this.water_bill_r_dw = water_bill_r_dw;
    }

    public Date getWater_bill_output_date() {
        return water_bill_output_date;
    }

    public void setWater_bill_output_date(Date water_bill_output_date) {
        this.water_bill_output_date = water_bill_output_date;
    }

    public Float getWater_bill_fee() {
        return water_bill_fee;
    }

    public void setWater_bill_fee(Float water_bill_fee) {
        this.water_bill_fee = water_bill_fee;
    }

    public Date getWater_bill_pay_date() {
        return water_bill_pay_date;
    }

    public void setWater_bill_pay_date(Date water_bill_pay_date) {
        this.water_bill_pay_date = water_bill_pay_date;
    }
}
