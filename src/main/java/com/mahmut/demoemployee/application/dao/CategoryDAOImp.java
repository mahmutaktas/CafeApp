package com.mahmut.demoemployee.application.dao;

import com.mahmut.demoemployee.application.dto.Category;
import com.mahmut.demoemployee.application.entity.CategoryEntity;
import com.mahmut.demoemployee.application.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Component
public class CategoryDAOImp implements CategoryDAO {

    @Autowired
    CategoryRepository categoryRepository;


    @Override
    public List<Category> findAll() {

        Iterator<CategoryEntity> categories = categoryRepository.findAll().iterator();
        List<Category> categoryList = new ArrayList<Category>();

        while(categories.hasNext()){

            CategoryEntity entity = categories.next();
            Category category = new Category();

            category.setId(entity.getId());
            category.setCategory(entity.getCategory());
            categoryList.add(category);
        }

        return categoryList;
    }


}
