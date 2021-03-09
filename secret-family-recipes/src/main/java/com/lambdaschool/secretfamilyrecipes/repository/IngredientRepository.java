package com.lambdaschool.secretfamilyrecipes.repository;

import com.lambdaschool.secretfamilyrecipes.models.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

    Ingredient findByNameIgnoreCase(String ingredientName);

//    Ingredient addIngredient(String name);
}
