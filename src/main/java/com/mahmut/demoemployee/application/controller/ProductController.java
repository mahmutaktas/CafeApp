package com.mahmut.demoemployee.application.controller;

import com.mahmut.demoemployee.application.dao.CategoryDAO;
import com.mahmut.demoemployee.application.dao.ProductDAO;
import com.mahmut.demoemployee.application.dao.UserDao;
import com.mahmut.demoemployee.application.dto.Category;
import com.mahmut.demoemployee.application.dto.Product;
import com.mahmut.demoemployee.application.entity.CategoryEntity;
import com.mahmut.demoemployee.application.entity.OrderEntity;
import com.mahmut.demoemployee.application.entity.ProductEntity;
import com.mahmut.demoemployee.application.repositories.CategoryRepository;
import com.mahmut.demoemployee.application.repositories.OrderRepository;
import com.mahmut.demoemployee.application.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
public class ProductController {

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    OrderRepository orderRepository;



    @Autowired
    private UserDao userDao;

    @RequestMapping("/admin/product/add")
    public String addProduct(Model model){
        ProductEntity product = new ProductEntity();
        List<Category> categories = categoryDAO.findAll();
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        return "admin/product-form";
    }

    @PostMapping("/admin/product/save")
    public String saveProduct(@ModelAttribute("product") ProductEntity product){

        productRepository.save(product);
        return "/admin/home";
    }

    @RequestMapping(value = "admin/orders/makeReady/{id}", method = RequestMethod.GET)
    public String makeReady(@PathVariable int id) {
        OrderEntity orderEntity = orderRepository.findByID(id);
        Date date = new Date();
        orderEntity.setUptadeTime(date);
        orderEntity.setStatus("ready to send");
        orderRepository.save(orderEntity);
        return "redirect:/admin/orders";
    }

    @RequestMapping("admin/orders")
    public String showOrders(Model model, @AuthenticationPrincipal UserDetails currentUser){

        userDao.findUserByEmail(currentUser.getUsername());

        model.addAttribute("orders", orderRepository.findAll());
        model.addAttribute("products",userDao.findUserByEmail(currentUser.getUsername()).getOrderEntities());
        return "/admin/orders";

    }

    @RequestMapping("/user/product/list")
    public String listProduct(Model model){
        List<Product> productList =  productDAO.findAll();
        for (int i=0; i < productList.size(); i++){
            CategoryEntity categoryEntity = categoryRepository.findByID(productList.get(i).getProductCategory());
            productList.get(i).setProCatName(categoryEntity.getCategory());
            productDAO.save(productList.get(i));
        }

        model.addAttribute("categories", categoryDAO.findAll());
        model.addAttribute("product", productRepository.findAll());
        return "/user/product-list";

    }

}
