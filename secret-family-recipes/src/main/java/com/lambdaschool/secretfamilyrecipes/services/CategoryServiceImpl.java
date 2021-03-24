package com.lambdaschool.secretfamilyrecipes.services;

import com.lambdaschool.secretfamilyrecipes.exceptions.ResourceNotFoundException;
import com.lambdaschool.secretfamilyrecipes.models.Category;
import com.lambdaschool.secretfamilyrecipes.models.Ingredient;
import com.lambdaschool.secretfamilyrecipes.models.Recipe;
import com.lambdaschool.secretfamilyrecipes.models.RecipeIngredients;
import com.lambdaschool.secretfamilyrecipes.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryrepos;

    @Autowired
    private IngredientService ingredientService;

    @Override
    public Category findCategoryById(long id) throws ResourceNotFoundException {
        return categoryrepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category id " + id + " not found."));
    }

    @Override
    public List<Category> findAll() {
        List<Category> list = new ArrayList<>();
        categoryrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        categoryrepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category id " + id + " not found."));
        categoryrepos.deleteById(id);
    }

    @Override
    public Category findByName(String name) {
        Category cat = categoryrepos.findCategoryByName(name.toLowerCase());
        if (cat == null) {
            throw new ResourceNotFoundException("Category name " + name + "not found.");
        }
        return cat;
    }

    @Transactional
    @Override
    public Category save(Category category) {
        Category newCategory = new Category();

        if (category.getCategoryid() != 0) {
            categoryrepos.findById((category.getCategoryid()))
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Category id " + category.getCategoryid() + " not found"));
            newCategory.setCategoryid(category.getCategoryid());
        }

        newCategory.setName(category.getName().toLowerCase());

        newCategory.getRecipes().clear();
        for (Recipe r : category.getRecipes()) {
            Recipe newRecipe = new Recipe(newCategory,
                    r.getRecipename(), r.getSource(), r.getInstructions());
            for (RecipeIngredients i : r.getIngredients()) {
                Ingredient ing = ingredientService.findIngredientById(i.getIngredient().getIngredientid());
                newRecipe.getIngredients().add(new RecipeIngredients(newRecipe, ing));
            }
            newCategory.getRecipes().add(newRecipe);
        }
        return categoryrepos.save(newCategory);
    }

    @Transactional
    @Override
    public Category update(Category category, long id) {
        Category currentCategory = findCategoryById(id);

        if (category.getName() != null) {
            currentCategory.setName((category.getName().toLowerCase()));
        }
        if (category.getRecipes().size() > 0) {
            currentCategory.getRecipes().clear();
            for (Recipe r : category.getRecipes()) {
                currentCategory.getRecipes().add(new Recipe(currentCategory,
                        r.getRecipename(), r.getSource(), r.getInstructions()));
            }
        }
        return categoryrepos.save(currentCategory);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void deleteAll() {
        categoryrepos.deleteAll();
    }
}
