package dao.entity;

public class Client {
    private String client_user;
    private String client_password;
    private String client_name;
    private String client_gender;
    private Integer client_age;
    private String client_phone;
    private String client_address;
    private Float client_money;

    public String getClient_user() {
        return client_user;
    }

    public void setClient_user(String client_user) {
        this.client_user = client_user;
    }

    public String getClient_password() {
        return client_password;
    }

    public void setClient_password(String client_password) {
        this.client_password = client_password;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getClient_gender() {
        return client_gender;
    }

    public void setClient_gender(String client_gender) {
        this.client_gender = client_gender;
    }

    public Integer getClient_age() {
        return client_age;
    }

    public void setClient_age(Integer client_age) {
        this.client_age = client_age;
    }

    public String getClient_phone() {
        return client_phone;
    }

    public void setClient_phone(String client_phone) {
        this.client_phone = client_phone;
    }

    public String getClient_address() {
        return client_address;
    }

    public void setClient_address(String client_address) {
        this.client_address = client_address;
    }

    public Float getClient_money() {
        return client_money;
    }

    public void setClient_money(Float client_money) {
        this.client_money = client_money;
    }
}
