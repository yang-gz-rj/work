package dao.entity;

import java.sql.Date;

public class ElectBill {

    private String elect_bill_number;
    private String device_number;
    private Integer elect_price_gradient;
    private Date elect_price_update_date;
    private Float elect_bill_init_value;
    private Float elect_bill_now_value;
    private String elect_bill_r_dw;
    private Date elect_bill_output_date;
    private Float elect_bill_fee;
    private Date elect_bill_pay_date;

    public String getElect_bill_number() {
        return elect_bill_number;
    }

    public void setElect_bill_number(String elect_bill_number) {
        this.elect_bill_number = elect_bill_number;
    }

    public String getDevice_number() {
        return device_number;
    }

    public void setDevice_number(String device_number) {
        this.device_number = device_number;
    }

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

    public Float getElect_bill_init_value() {
        return elect_bill_init_value;
    }

    public void setElect_bill_init_value(Float elect_bill_init_value) {
        this.elect_bill_init_value = elect_bill_init_value;
    }

    public Float getElect_bill_now_value() {
        return elect_bill_now_value;
    }

    public void setElect_bill_now_value(Float elect_bill_now_value) {
        this.elect_bill_now_value = elect_bill_now_value;
    }

    public String getElect_bill_r_dw() {
        return elect_bill_r_dw;
    }

    public void setElect_bill_r_dw(String elect_bill_r_dw) {
        this.elect_bill_r_dw = elect_bill_r_dw;
    }

    public Date getElect_bill_output_date() {
        return elect_bill_output_date;
    }

    public void setElect_bill_output_date(Date elect_bill_output_date) {
        this.elect_bill_output_date = elect_bill_output_date;
    }

    public Float getElect_bill_fee() {
        return elect_bill_fee;
    }

    public void setElect_bill_fee(Float elect_bill_fee) {
        this.elect_bill_fee = elect_bill_fee;
    }

    public Date getElect_bill_pay_date() {
        return elect_bill_pay_date;
    }

    public void setElect_bill_pay_date(Date elect_bill_pay_date) {
        this.elect_bill_pay_date = elect_bill_pay_date;
    }
}
