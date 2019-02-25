package com.mahmut.demoemployee.application.repositories;

import com.mahmut.demoemployee.application.entity.OrderEntity;
import com.mahmut.demoemployee.application.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);

    @Query("from UserEntity p where p.id=:id")
    UserEntity findOne(@Param("id") int userid);


}
