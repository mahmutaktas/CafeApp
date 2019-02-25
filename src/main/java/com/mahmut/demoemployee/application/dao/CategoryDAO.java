package com.mahmut.demoemployee.application.dao;

import com.mahmut.demoemployee.application.dto.Category;

import java.util.List;
public interface CategoryDAO {

    List<Category> findAll();

}
