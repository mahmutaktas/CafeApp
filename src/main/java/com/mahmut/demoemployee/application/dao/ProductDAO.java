package com.mahmut.demoemployee.application.dao;

import com.mahmut.demoemployee.application.dto.Product;
import com.mahmut.demoemployee.application.entity.ProductEntity;

import java.util.List;

public interface ProductDAO {

    List<Product> findAll();
    Product findByID(int id);
    void save(Product product);

    List<Product> listByCategory(int categoryid);
}
