package com.mahmut.demoemployee.application.controller;

import com.mahmut.demoemployee.application.dao.CategoryDAO;
import com.mahmut.demoemployee.application.entity.CategoryEntity;
import com.mahmut.demoemployee.application.repositories.CategoryRepository;
import com.mahmut.demoemployee.application.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    private CategoryRepository categoryRepository;


    /*

    sıfırdan category oluştur bir model
    oluşturup boş categoryi o modele at form ile bağlamak için


     */
    @RequestMapping("/form")
    public String addCategory(Model model){
        CategoryEntity category = new CategoryEntity();
        model.addAttribute("categoryy", category);

        return "admin/category-form";
    }

    /*
    category-formda doldurduğun veriler save'e gönderip kaydet
     */
    @PostMapping("/saveCategory")
    public String saveCategory(@ModelAttribute("categoryy") CategoryEntity category){
        categoryRepository.save(category);//save methodu crud repositorisinden hazır geliyor
        // . sadece category repositor ile özelleşiyor ve category dao da içi doluyor
        return "redirect:/admin/home";
    }

}
