package com.lambdaschool.secretfamilyrecipes.controllers;

import com.lambdaschool.secretfamilyrecipes.models.Ingredient;
import com.lambdaschool.secretfamilyrecipes.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @GetMapping(value = "/ingredients", produces = "application/json")
    public ResponseEntity<?> listIngredients() {
        List<Ingredient> allIngredients = ingredientService.findAll();
        return new ResponseEntity<>(allIngredients, HttpStatus.OK);
    }

    @GetMapping(value = "/ingredient/{ingredId}",
        produces = "application/json")
    public ResponseEntity<?> getIngredientById(@PathVariable long ingredId) {
        Ingredient ingred = ingredientService.findIngredientById(ingredId);
        return new ResponseEntity<>(ingred, HttpStatus.OK);
    }

    @GetMapping(value = "/ingredient/name/{ingredientName}",
        produces = "application/json")
    public ResponseEntity<?> getIngredientByName(
            @PathVariable String ingredientName) {
        Ingredient ingred = ingredientService.findByName(ingredientName);
        return new ResponseEntity<>(ingred, HttpStatus.OK);
    }

    @PostMapping(value = "/ingredient", consumes = "application/json")
    public ResponseEntity<?> addNewIngredient(
            @Valid @RequestBody Ingredient newIngredient) {
        newIngredient.setIngredientid(0);
        newIngredient = ingredientService.save(newIngredient);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newIngredientURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{ingredientid")
                .buildAndExpand(newIngredient.getIngredientid())
                .toUri();
        responseHeaders.setLocation(newIngredientURI);

        return new ResponseEntity<>(null,
                responseHeaders,
                HttpStatus.CREATED);
    }

    @PutMapping(value = "/ingredient/{ingredientid}",
            consumes = "application/json")
    public ResponseEntity<?> updateIngredient(
            @Valid
            @PathVariable long ingredientid,
            @RequestBody Ingredient newIngredient) {
        newIngredient = ingredientService.update(ingredientid,newIngredient);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
