package com.lambdaschool.secretfamilyrecipes.repository;

import com.lambdaschool.secretfamilyrecipes.models.Ingredient;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

    Ingredient findByNameIgnoreCase(String ingredientName);

    @Transactional
    @Modifying
    @Query(value = "UPDATE ingredients SET name = :name, lastmodifiedby = :uname, lastmodifieddate = CURRENT_TIMESTAMP WHERE ingredientid = :ingredientid",
        nativeQuery = true)
    void updateIngredientName(String uname, long ingredientid, String name);

//    Ingredient addIngredient(String name);
}
