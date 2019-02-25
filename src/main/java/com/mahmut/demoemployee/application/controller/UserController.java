package com.mahmut.demoemployee.application.controller;

import com.mahmut.demoemployee.application.dao.UserDao;
import com.mahmut.demoemployee.application.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/user")
public class UserController{

    @Autowired
    private UserDao userDao;

    @RequestMapping("/form")
    public String showUserForm(Model model){
        UserEntity user = new UserEntity();
        model.addAttribute("user", user);
        return "user-form";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") UserEntity user) {
    	userDao.save(user);
    	return "redirect:/";
    }


    /*
    daha önce oluşturduğumuz user modelini buluyor.
     */
    @RequestMapping("/list")
    public String listUsers(Model model){

        model.addAttribute("user", userDao.findAll());//o user modelini ekrana yazdırmak için
        // attribute ekliyoruz ve findAll metodulya dbde olan bütün userları ona atıyoruz.

        return "/admin/user-list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable int id) {


        userDao.deleteUser(id);

    	return "redirect:/";
    }

}
