package com.mahmut.demoemployee.application.dao;

import com.mahmut.demoemployee.application.dto.Product;
import com.mahmut.demoemployee.application.entity.ProductEntity;
import com.mahmut.demoemployee.application.repositories.CategoryRepository;
import com.mahmut.demoemployee.application.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
@ComponentScan(basePackages = "com.mahmut.demoemployee.application.repositories")
public class ProductDAOImp implements ProductDAO {


    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> findAll() {

        Iterator<ProductEntity> products = productRepository.findAll().iterator();
        List<Product> productList = new ArrayList<Product>();

        while (products.hasNext()){

            ProductEntity entity = products.next();
            Product product = new Product();

            product.setId(entity.getId());
            product.setProductName(entity.getProductName());
            product.setProductPrice(entity.getProductPrice());
            product.setProductCategory(entity.getProductCategory());

            productList.add(product);

        }

        return productList;
    }

    @Override
    public Product findByID(int id) {
        ProductEntity productEntity = productRepository.findByID(id);
        Product product = new Product();

        product.setId(productEntity.getId());
        product.setProductName(productEntity.getProductName());
        product.setProductPrice(productEntity.getProductPrice());
        product.setProductCategory(productEntity.getProductCategory());

        return product;
    }

    @Override
    public void save(Product product) {
        ProductEntity productEntity = productRepository.findByID(product.getId());
        productEntity.setProCatName(product.getProCatName());
        productRepository.save(productEntity);

    }

    @Override
    public List<Product> listByCategory(int categoryid) {

        Iterator<ProductEntity> productEntityList = productRepository.listByCategory(categoryid).iterator();
        List<Product> productList = new ArrayList<Product>();

        while (productEntityList.hasNext()){

            ProductEntity entity = productEntityList.next();
            Product product = new Product();

            product.setId(entity.getId());
            product.setProductCategory(entity.getProductCategory());
            product.setProductPrice(entity.getProductPrice());
            product.setProductName(entity.getProductName());

            productList.add(product);
        }

        return productList;
    }


}
