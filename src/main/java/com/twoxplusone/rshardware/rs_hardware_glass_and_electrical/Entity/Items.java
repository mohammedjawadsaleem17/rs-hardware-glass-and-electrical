package com.twoxplusone.rshardware.rs_hardware_glass_and_electrical.Entity;

import org.springframework.data.mongodb.core.mapping.Document;


public class Items {
    private String sno;
    private String id;
    private String item;
    private String hsn;
    private Double rate;
    private Integer qty;
    private Double taxableValue;
    private Double taxAmount;
    private Double total;

    public Items(String sno, String id, String item, String hsn, Double rate, Integer qty, Double taxableValue, Double taxAmount, Double total) {
        this.sno = sno;
        this.id = id;
        this.item = item;
        this.hsn = hsn;
        this.rate = rate;
        this.qty = qty;
        this.taxableValue = taxableValue;
        this.taxAmount = taxAmount;
        this.total = total;
    }

    public Items() {
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getHsn() {
        return hsn;
    }

    public void setHsn(String hsn) {
        this.hsn = hsn;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Double getTaxableValue() {
        return taxableValue;
    }

    public void setTaxableValue(Double taxableValue) {
        this.taxableValue = taxableValue;
    }

    public Double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
