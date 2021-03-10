package com.lambdaschool.secretfamilyrecipes.repository;

import com.lambdaschool.secretfamilyrecipes.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    Category findCategoryByName(String name);

//    List<Category> findCategoryContainingIgnoreCase(String name);

}
