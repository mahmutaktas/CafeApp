package com.mahmut.demoemployee.application.repositories;

import com.mahmut.demoemployee.application.entity.OrderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, Long> {

    @Query("from OrderEntity o where o.id=:orderID")
    OrderEntity findByID(@Param("orderID") int id);

    @Query("from OrderEntity o where o.userID=:userID")
    List<OrderEntity> findByUser(@Param("userID") int id);
}
