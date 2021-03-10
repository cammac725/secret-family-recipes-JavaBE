package com.lambdaschool.secretfamilyrecipes.services;

import com.lambdaschool.secretfamilyrecipes.models.Ingredient;

import java.util.List;

public interface IngredientService {

    List<Ingredient> findAll();

    Ingredient findIngredientById(long ingredId);

    Ingredient findByName(String ingredientName);

    Ingredient save(Ingredient ingredient);

    Ingredient update(long ingredientid, Ingredient ingredient);
}
