package com.lambdaschool.secretfamilyrecipes.services;

import com.lambdaschool.secretfamilyrecipes.exceptions.ResourceFoundException;
import com.lambdaschool.secretfamilyrecipes.exceptions.ResourceNotFoundException;
import com.lambdaschool.secretfamilyrecipes.models.Ingredient;
import com.lambdaschool.secretfamilyrecipes.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "ingredientService")
public class IngredientServiceImpl implements IngredientService{

    @Autowired
    IngredientRepository ingredientrepos;

    @Autowired
    private UserAuditing userAuditing;

    @Override
    public List<Ingredient> findAll() {
        List<Ingredient> ingredList = new ArrayList<>();
        ingredientrepos.findAll()
                .iterator()
                .forEachRemaining(ingredList::add);
        return ingredList;
    }

    @Override
    public Ingredient findIngredientById(long ingredId) {
        return ingredientrepos.findById(ingredId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Ingredient id " + ingredId + " not found"));
    }

    @Override
    public Ingredient findByName(String ingredientName) {
        Ingredient ingred = ingredientrepos.findByIngredientnameIgnoreCase(ingredientName);
        if (ingred != null) {
            return ingred;
        } else {
            throw new ResourceNotFoundException(
                    "Ingredient " + ingredientName + " not found");
        }
    }

    @Transactional
    @Override
    public Ingredient save(Ingredient ingredient) {

        if (ingredient.getRecipes().size() > 0) {
            throw new ResourceFoundException("Recipe ingredients are not updated through Ingredient");
        }
        return ingredientrepos.save(ingredient);
    }

    @Transactional
    @Override
    public Ingredient update(long ingredientid, Ingredient ingredient) {
        if (ingredient.getName() == null) {
            throw new ResourceNotFoundException("No ingredient name found to update.");
        }
        if (ingredient.getRecipes().size() > 0) {
            throw new ResourceFoundException("Recipe ingredients are not updated through Ingredient");
        }

        Ingredient newIngredient = findIngredientById(ingredientid);

        ingredientrepos.updateIngredientName(userAuditing.getCurrentAuditor()
                .get(),
                ingredientid,
                ingredient.getName());

        return findIngredientById(ingredientid);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void deleteAll() {
        ingredientrepos.deleteAll();
    }
}
