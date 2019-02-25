package com.mahmut.demoemployee.application.repositories;

import com.mahmut.demoemployee.application.entity.CategoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {

    @Query("from CategoryEntity c where c.id=:categoryID")
    CategoryEntity findByID (@Param("categoryID") int categoryID);
}
