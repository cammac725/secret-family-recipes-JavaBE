package com.lambdaschool.secretfamilyrecipes.services;

import com.lambdaschool.secretfamilyrecipes.models.Recipe;
import com.lambdaschool.secretfamilyrecipes.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service(value = "recipeService")
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository reciperepos;

    @Override
    public List<Recipe> findAll() {
        return null;
    }

    @Override
    public Recipe findRecipeById(long id) {
        return null;
    }

    @Override
    public List<Recipe> findByNameContaining(String recipeName) {
        return reciperepos.findByRecipeNameContainingIgnoreCase(recipeName.toLowerCase());
    }

    @Override
    public void deleteById(long id) {
    }

    @Override
    public Recipe save(Recipe recipe) {
        return null;
    }

    @Override
    public Recipe update(Recipe recipe, long id) {
        return null;
    }
}
