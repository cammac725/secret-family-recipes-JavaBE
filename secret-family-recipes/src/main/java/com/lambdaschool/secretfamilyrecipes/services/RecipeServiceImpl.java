package com.lambdaschool.secretfamilyrecipes.services;

import com.lambdaschool.secretfamilyrecipes.exceptions.ResourceNotFoundException;
import com.lambdaschool.secretfamilyrecipes.models.Ingredient;
import com.lambdaschool.secretfamilyrecipes.models.Recipe;
import com.lambdaschool.secretfamilyrecipes.models.RecipeIngredients;
import com.lambdaschool.secretfamilyrecipes.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "recipeService")
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository reciperepos;

    @Autowired
    private IngredientService ingredientService;
    
    @Override
    public List<Recipe> findAll() {
        List<Recipe> recList = new ArrayList<>();
        reciperepos.findAll().iterator().forEachRemaining(recList::add);
        return recList;
    }

    @Override
    public Recipe findRecipeById(long id) throws ResourceNotFoundException {
        return reciperepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Recipe id " + id + " not found"));
    }

    @Override
    public List<Recipe> findByNameContaining(String recipeName) {
        return reciperepos.findByRecipenameContainingIgnoreCase(recipeName.toLowerCase());
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        reciperepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Recipe id " + id + " not found"));
        reciperepos.deleteById(id);
    }

    @Transactional
    @Override
    public Recipe save(Recipe recipe) {
        Recipe newRecipe = new Recipe();

        if (recipe.getRecipeid() != 0) {
            reciperepos.findById((recipe.getRecipeid()))
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Category id " + recipe.getRecipeid() + " not found"));
            newRecipe.setRecipeid(recipe.getRecipeid());
        }

        newRecipe.setCategory(recipe.getCategory());
        newRecipe.setRecipename(recipe.getRecipename().toLowerCase());
        newRecipe.setSource(recipe.getSource());
        newRecipe.setInstructions(recipe.getInstructions());

        newRecipe.getIngredients().clear();
        for (RecipeIngredients ri : recipe.getIngredients()) {
            Ingredient addIngredient = ingredientService.findIngredientById(
                    ri.getIngredient().getIngredientid());
            newRecipe.getIngredients().add(new RecipeIngredients(
                    newRecipe, addIngredient));

        }
        return reciperepos.save(newRecipe);
    }

    @Override
    public Recipe update(Recipe recipe, long id) {
        Recipe currentRecipe = findRecipeById(id);
        if (recipe.getRecipename() != null) {
            currentRecipe.setRecipename(recipe.getRecipename().toLowerCase());
        }
        currentRecipe.setCategory(recipe.getCategory());
        currentRecipe.setSource(recipe.getSource());
        currentRecipe.setInstructions(recipe.getInstructions());

        if (recipe.getIngredients().size() > 0) {
            currentRecipe.getIngredients().clear();
            for (RecipeIngredients ri : recipe.getIngredients()) {
                Ingredient addIngredient = ingredientService.findIngredientById(
                        ri.getIngredient().getIngredientid());
                currentRecipe.getIngredients().add(new RecipeIngredients(
                        currentRecipe, addIngredient));
            }
        }
        return reciperepos.save(currentRecipe);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void deleteAll() {
        reciperepos.deleteAll();
    }

}
