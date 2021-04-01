package com.lambdaschool.secretfamilyrecipes.controllers;

import com.lambdaschool.secretfamilyrecipes.models.Category;
import com.lambdaschool.secretfamilyrecipes.services.CategoryService;
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
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // http://localhost:2019/categories/categories
    @GetMapping(value = "/categories", produces = "application/json")
    public ResponseEntity<?> listAllCategories() {
        List<Category> catList = categoryService.findAll();
        return new ResponseEntity<>(catList, HttpStatus.OK);
    }
    // http://localhost:2019/categories//category/{categoryid}
    @GetMapping(value = "/category/{categoryid}", produces = "application/json")
    public ResponseEntity<?> getCategoryById(@PathVariable Long categoryid) {
        Category cat = categoryService.findCategoryById(categoryid);
        return new ResponseEntity<>(cat, HttpStatus.OK);
    }

    // http://localhost:2019/categories/category
    @PostMapping(value = "/category", consumes = "application/json")
    public ResponseEntity<?> addNewCategory(
            @Valid
            @RequestBody Category newcategory) throws URISyntaxException {
        newcategory.setCategoryid(0);
        newcategory = categoryService.save(newcategory);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCategoryURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{categoryid")
                .buildAndExpand(newcategory.getCategoryid())
                .toUri();
        responseHeaders.setLocation(newCategoryURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }
}
