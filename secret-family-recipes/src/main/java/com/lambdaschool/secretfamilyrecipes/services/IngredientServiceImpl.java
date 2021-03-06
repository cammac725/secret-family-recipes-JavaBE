package com.lambdaschool.secretfamilyrecipes.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service(value = "ingredientService")
public class IngredientServiceImpl
        implements IngredientService{
}
