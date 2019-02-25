package com.mahmut.demoemployee.application.dao;

import com.mahmut.demoemployee.application.dto.User;
import com.mahmut.demoemployee.application.entity.UserEntity;

import java.util.List;

public interface UserDao {

    User findUserByEmail(String email);
    void save(UserEntity user);
    void update(UserEntity userEntity);
    List<User> findAll();
    User findOne(int userid);
    void deleteUser(int userid);
    /*sdgdfgdfhdg*/

}
