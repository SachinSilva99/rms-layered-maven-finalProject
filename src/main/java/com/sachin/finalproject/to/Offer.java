package com.sachin.finalproject.to;

public class Offer {
    double percentage;
    String des;
    String code;

    public Offer() {
    }

    public Offer(double percentage, String des, String code) {
        this.percentage = percentage;
        this.des = des;
        this.code = code;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "percentage=" + percentage +
                ", des='" + des + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
