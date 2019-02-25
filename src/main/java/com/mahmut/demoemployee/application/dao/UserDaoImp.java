package com.mahmut.demoemployee.application.dao;

import com.mahmut.demoemployee.application.dto.Product;
import com.mahmut.demoemployee.application.dto.User;
import com.mahmut.demoemployee.application.entity.ProductEntity;
import com.mahmut.demoemployee.application.entity.RoleEntity;
import com.mahmut.demoemployee.application.entity.UserEntity;
import com.mahmut.demoemployee.application.repositories.ProductRepository;
import com.mahmut.demoemployee.application.repositories.RoleRepository;
import com.mahmut.demoemployee.application.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@ComponentScan(basePackages = "com.mahmut.demoemployee.application.config")
public class UserDaoImp implements UserDao {


    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public User findUserByEmail(String email) {

        User user = new User();
        UserEntity userEntity = userRepository.findByEmail(email);

        if(userEntity == null){
            return null;
        }

        user.setActive(userEntity.getActive());
        user.setEmail(userEntity.getEmail());
        user.setFirstName(userEntity.getFirstName());
        user.setId(userEntity.getId());
        user.setLastName(userEntity.getLastName());
        user.setPassword(userEntity.getPassword());

        return user;

    }


    @Override
    public void save(UserEntity user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        RoleEntity userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    @Override
    public void update(UserEntity userEntity){
        userRepository.save(userEntity);
    }

    @Override
    public List<User> findAll() {

        Iterator<UserEntity> userEntityIterator = userRepository.findAll().iterator();
        List<User> userList = new ArrayList<User>();

        while (userEntityIterator.hasNext()){

            UserEntity entity = userEntityIterator.next();
            User user = new User();

            user.setActive(entity.getActive());
            user.setEmail(entity.getEmail());
            user.setFirstName(entity.getFirstName());
            user.setId(entity.getId());
            user.setLastName(entity.getLastName());
            user.setPassword(entity.getPassword());

            userList.add(user);
        }

        return userList;
    }

	@Override
	public User findOne(int userid) {

        UserEntity userEntity = userRepository.findOne(userid);
        User user = new User();

        user.setActive(userEntity.getActive());
        user.setEmail(userEntity.getEmail());
        user.setFirstName(userEntity.getFirstName());
        user.setId(userEntity.getId());
        user.setLastName(userEntity.getLastName());
        user.setPassword(userEntity.getPassword());

		return user;
	}

	@Override
	public void deleteUser(int userid){

        UserEntity userEntity = userRepository.findOne(userid);
        userRepository.delete(userEntity);

    }


}