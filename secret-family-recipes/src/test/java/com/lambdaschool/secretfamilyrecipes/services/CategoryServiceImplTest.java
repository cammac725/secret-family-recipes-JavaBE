package com.lambdaschool.secretfamilyrecipes.services;

import com.lambdaschool.secretfamilyrecipes.SecretFamilyRecipesApplication;
import com.lambdaschool.secretfamilyrecipes.exceptions.ResourceNotFoundException;
import com.lambdaschool.secretfamilyrecipes.models.Category;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SecretFamilyRecipesApplication.class)
public class CategoryServiceImplTest {

    @Autowired
    private CategoryService categoryService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        List<Category> categoryList = categoryService.findAll();
        for (Category c : categoryList) {
            System.out.println(c.getCategoryid() + " " + c.getName());
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findCategoryById() {
        assertEquals("entree", categoryService.findCategoryById(38).getName());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void findCategoryByIdNotFound() {
        assertEquals("entree", categoryService.findCategoryById(3).getName());
    }

    @Test
    public void findAll() {
        assertEquals(5, categoryService.findAll().size());
    }

    @Test
    public void deleteById() {
        categoryService.deleteById(41);
        assertEquals(4, categoryService.findAll().size());
    }

    @Test
    public void findByName() {
        assertEquals(38, categoryService.findByName("entree").getCategoryid());
    }
}