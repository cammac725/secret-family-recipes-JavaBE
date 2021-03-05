package com.lambdaschool.secretfamilyrecipes.services;

import com.lambdaschool.secretfamilyrecipes.models.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

//    List<Category> findByNameContaining(String name);

    Category findCategoryById(long id);

    Category findByName(String name);

    void deleteById(long id);

    Category save(Category category);

    Category update(Category category, long id);

    void deleteAll();
}
