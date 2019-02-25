package com.mahmut.demoemployee.application.dto;

import javax.persistence.*;

public class Product {

    private int id;
    private String productName;
    private int productPrice;
    private int productCategory;
    private String proCatName;

    public void setProductCategory(int productCategory) {
        this.productCategory = productCategory;
    }

    public int getProductCategory() {
        return productCategory;
    }

    public String getProCatName() {
        return proCatName;
    }

    public void setProCatName(String proCatName) {
        this.proCatName = proCatName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }





}

