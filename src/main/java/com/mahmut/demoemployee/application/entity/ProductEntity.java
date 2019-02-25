package com.mahmut.demoemployee.application.entity;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.*;

@Entity
@Table(name="product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="product_name")
    private String productName;

    @Column(name="product_price")
    private int productPrice;

    @Column(name="product_category")
    private int productCategory;

    @Column(name = "product_category_name")
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

