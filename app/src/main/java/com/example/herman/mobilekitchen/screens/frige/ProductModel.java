package com.example.herman.mobilekitchen.screens.frige;

public class ProductModel {
    private int id;
    private int drawable;
    private String prodName;
    private long amount;
    private long expireDate;
    private String measure;
    private String type;

    public ProductModel() {
    }

    public ProductModel(int id) {
        this.id = id;
    }

    public ProductModel(int drawable, String prodName, long expireDate, long amount, String measure, String type) {
        this.drawable = drawable;
        this.prodName = prodName;
        this.expireDate = expireDate;
        this.amount = amount;
        this.measure = measure;
        this.type = type;
    }

    public ProductModel(int drawable, String prodName, long expireDate, long amount, String measure, String type, int id) {
        this.drawable = drawable;
        this.prodName = prodName;
        this.expireDate = expireDate;
        this.amount = amount;
        this.measure = measure;
        this.type = type;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public long getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(long expireDate) {
        this.expireDate = expireDate;
    }

    public void setType(String type) {
        this.measure = type;
    }

    public String getType() {
        return type;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
