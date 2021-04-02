package com.lambdaschool.secretfamilyrecipes.services;

import com.lambdaschool.secretfamilyrecipes.SecretFamilyRecipesApplication;
import com.lambdaschool.secretfamilyrecipes.exceptions.ResourceNotFoundException;
import com.lambdaschool.secretfamilyrecipes.models.Recipe;
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
public class RecipeServiceImplTest {

    @Autowired
    private RecipeService recipeService;

//    @Autowired
//    private RecipeRepository reciperepos;
//
//    @MockBean
//    HelperFunctions helperFunctions;
//
//    private List<Recipe> recipeList;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);


        List<Recipe> recipeList = recipeService.findAll();
        for (Recipe r : recipeList) {
            System.out.println(r.getRecipeid() + " " + r.getRecipename());
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findAll() {
        assertEquals(6, recipeService.findAll().size());
    }

    @Test
    public void findRecipeById() {
        assertEquals("test-tacos",
                recipeService.findRecipeById(39).getRecipename());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void findRecipeByIdNotFound() {
        assertEquals("test-tacos",
                recipeService.findRecipeById(2).getRecipename());
    }

    @Test
    public void findByNameContaining() {
        assertEquals(3, recipeService.findByNameContaining("co").size());
    }

    @Test
    public void deleteById() {
        recipeService.deleteById(39);
        assertEquals(4, recipeService.findAll().size());
    }

    @Test
    public void save() {
    }

    @Test
    public void update() {
    }

    @Test
    public void deleteAll() {
    }
}