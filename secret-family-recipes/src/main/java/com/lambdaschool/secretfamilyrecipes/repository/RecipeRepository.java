package com.lambdaschool.secretfamilyrecipes.repository;

import com.lambdaschool.secretfamilyrecipes.models.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    List<Recipe> findByRecipenameContainingIgnoreCase(String toLowerCase);

//    Recipe findRecipeByName(String name);
//
//    Recipe addRecipe();
//
//    void updateRecipe();
}
