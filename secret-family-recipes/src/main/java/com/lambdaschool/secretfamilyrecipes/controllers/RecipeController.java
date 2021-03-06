package com.lambdaschool.secretfamilyrecipes.controllers;

import com.lambdaschool.secretfamilyrecipes.models.Recipe;
import com.lambdaschool.secretfamilyrecipes.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping(value = "/recipes", produces = "application/json")
    public ResponseEntity<?> listAllRecipes() {
        List<Recipe> myList = recipeService.findAll();
        return new ResponseEntity<>(myList, HttpStatus.OK);
    }

    @GetMapping(value = "/recipes/{recipeId}", produces = "application/json")
    public ResponseEntity<?> getRecipeById(@PathVariable Long recipeId) {
        Recipe r = recipeService.findRecipeById(recipeId);
        return new ResponseEntity<>(r, HttpStatus.OK);
    }

    @GetMapping(value = "/recipes/name/like/{recipeName}",
        produces = "application/json")
    public ResponseEntity<?> getRecipeLikeName(
            @PathVariable String recipeName
    ) {
        List<Recipe> rList = recipeService.findByNameContaining(recipeName);
        return new ResponseEntity<>(rList, HttpStatus.OK);
    }

    @PostMapping(value = "/recipe", consumes = "application/json")
    public ResponseEntity<?> addNewRecipe(
            @Valid
            @RequestBody Recipe newrecipe) throws URISyntaxException {
        newrecipe.setRecipeid(0);
        newrecipe = recipeService.save(newrecipe);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{recipeid}")
                .buildAndExpand(newrecipe.getRecipeid())
                .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null,
                responseHeaders, HttpStatus.CREATED);
    }

    @PatchMapping(value = "/recipe/{recipeId}",
        consumes = "application/json")
    public ResponseEntity<?> updateRecipe(
            @RequestBody Recipe updateRecipe,
            @PathVariable long recipeId) {
        recipeService.update(updateRecipe,recipeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/recipe/{recipeId}")
    public ResponseEntity<?> deleteRecipeById(
            @PathVariable long recipeId) {
        recipeService.deleteById(recipeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
