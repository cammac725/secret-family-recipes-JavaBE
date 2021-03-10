package com.lambdaschool.secretfamilyrecipes.services;

import com.lambdaschool.secretfamilyrecipes.models.Recipe;

import java.util.List;

public interface RecipeService {

    List<Recipe> findAll();

    Recipe findRecipeById(long id);

    void deleteById(long id);

    Recipe save(Recipe recipe);

    Recipe update(Recipe recipe, long id);

    List<Recipe> findByNameContaining(String recipeName);
}
