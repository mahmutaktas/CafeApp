package com.mahmut.demoemployee.application.controller;

import com.mahmut.demoemployee.application.dao.CategoryDAO;
import com.mahmut.demoemployee.application.dao.ProductDAO;
import com.mahmut.demoemployee.application.dao.UserDaoImp;
import com.mahmut.demoemployee.application.dto.Category;
import com.mahmut.demoemployee.application.dto.Product;
import com.mahmut.demoemployee.application.dto.User;
import com.mahmut.demoemployee.application.entity.OrderEntity;
import com.mahmut.demoemployee.application.entity.UserEntity;
import com.mahmut.demoemployee.application.repositories.OrderRepository;
import com.mahmut.demoemployee.application.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping("/user")
public class UserHomeController {

    @Autowired
    ProductDAO productDAO;

    @Autowired
    private UserDaoImp userDao;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CategoryDAO categoryDAO;

    @Autowired
    UserRepository userRepository;

    UserEntity userEntity = new UserEntity();


    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userDao.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getFirstName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName("user/home");
        return modelAndView;
    }

    @RequestMapping(value = "/sort",method = RequestMethod.POST)
    public String showProduct(@ModelAttribute("abc") int id, Model model)
    {

        List<Product> productsByCategory;

        if(id==0 ){
            productsByCategory = productDAO.findAll();
        }else {
            productsByCategory = productDAO.listByCategory(id);
        }
        List<Category> categories = categoryDAO.findAll();
        model.addAttribute("product",productsByCategory);
        model.addAttribute("categories",categories);
        model.addAttribute("selectedProduct",id);
        return "/user/product-list";
    }

    @RequestMapping("/order/list")
    public String orderProductList(Model model){
        model.addAttribute("products", productDAO.findAll());

        return "/user/order";
    }

    @RequestMapping(value = "/order/giveOrder", method = RequestMethod.POST)
    public String giveOrder(@RequestParam("orders") int[] productList, @AuthenticationPrincipal UserDetails currentUser){

        List<Product> products = new ArrayList<Product>();
        Set<OrderEntity> orderEntities = new HashSet<OrderEntity>();
        userEntity = userRepository.findByEmail(currentUser.getUsername());
        Date date = new Date();

        for(int i=0; i<productList.length; i++){
            OrderEntity orderEntity = new OrderEntity();
            products.add(productDAO.findByID(productList[i]));
            orderEntity.setStatus("preparing");
            orderEntity.setProductName(products.get(i).getProductName());
            orderEntity.setUserID(userEntity.getId());
            orderEntity.setStartTime(date);
            orderEntities.add(orderEntity);
        }
        userEntity.setOrderEntity(orderEntities);
        userDao.update(userEntity);

        return "redirect:/user/home";
    }

    @RequestMapping("/order/status")
    public String orderStatus(Model model, @AuthenticationPrincipal UserDetails currentUser){

        int id = userDao.findUserByEmail(currentUser.getUsername()).getId();
        model.addAttribute("orders", orderRepository.findByUser(id));

        return "/user/order-status";
    }

}