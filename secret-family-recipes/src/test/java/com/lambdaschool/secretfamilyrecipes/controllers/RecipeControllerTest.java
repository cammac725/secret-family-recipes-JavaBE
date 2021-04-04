package com.lambdaschool.secretfamilyrecipes.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.secretfamilyrecipes.models.Category;
import com.lambdaschool.secretfamilyrecipes.models.Ingredient;
import com.lambdaschool.secretfamilyrecipes.models.Recipe;
import com.lambdaschool.secretfamilyrecipes.models.RecipeIngredients;
import com.lambdaschool.secretfamilyrecipes.services.RecipeService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class RecipeControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private RecipeService recipeService;

    private List<Recipe> recipeList;

    @Before
    public void setUp() throws Exception {
        recipeList = new ArrayList<>();

        Ingredient ing1 = new Ingredient("corn tortillas");
        Ingredient ing2 = new Ingredient("shrimp");
        Ingredient ing3 = new Ingredient("grated cheese");
        Ingredient ing4 = new Ingredient("chopped tomatoes");
        Ingredient ing5 = new Ingredient("shredded cabbage");
        Ingredient ing6 = new Ingredient("salsa");
        Ingredient ing7 = new Ingredient("chopped broccoli");
        Ingredient ing8 = new Ingredient("cooked rice");
        Ingredient ing9 = new Ingredient("salt");
        Ingredient ing10 = new Ingredient("black pepper");
        Ingredient ing11 = new Ingredient("sliced red pepper");
        Ingredient ing12 = new Ingredient("tamari");
        Ingredient ing13 = new Ingredient("cup of flour");
        Ingredient ing14 = new Ingredient("baking powder");
        Ingredient ing15 = new Ingredient("cocoa");
        Ingredient ing16 = new Ingredient("melted chocolate");
        Ingredient ing17 = new Ingredient("eggs");

        ing1.setIngredientid(1);
        ing2.setIngredientid(2);
        ing3.setIngredientid(3);
        ing4.setIngredientid(4);
        ing5.setIngredientid(5);
        ing6.setIngredientid(6);
        ing7.setIngredientid(7);
        ing8.setIngredientid(8);
        ing9.setIngredientid(9);
        ing10.setIngredientid(10);
        ing11.setIngredientid(11);
        ing12.setIngredientid(12);
        ing13.setIngredientid(13);
        ing14.setIngredientid(14);
        ing15.setIngredientid(15);
        ing16.setIngredientid(16);
        ing17.setIngredientid(17);

        Category c1 = new Category("entree");
        Category c2 = new Category("appetizer");
        Category c3 = new Category("dessert");
        Category c4 = new Category("soup");
        Category c5 = new Category("vegan");

        c1.setCategoryid(1);
        c2.setCategoryid(2);
        c3.setCategoryid(3);
        c4.setCategoryid(4);
        c5.setCategoryid(5);

        Recipe rec1 = new Recipe(c1,"test-tacos", "Cousin Juan", "Add together, eat.");
        rec1.getIngredients().add(new RecipeIngredients(rec1, ing1));
        rec1.getIngredients().add(new RecipeIngredients(rec1, ing2));
        rec1.getIngredients().add(new RecipeIngredients(rec1, ing3));
        rec1.getIngredients().add(new RecipeIngredients(rec1, ing4));
        rec1.getIngredients().add(new RecipeIngredients(rec1, ing5));
        rec1.getIngredients().add(new RecipeIngredients(rec1, ing6));
        rec1.setRecipeid(101);
        recipeList.add(rec1);

        Recipe rec2 = new Recipe(c5, "Broccoli Casserole", "Barb", "Add together, bake, eat");
        rec2.getIngredients().add(new RecipeIngredients(rec2, ing7));
        rec2.getIngredients().add(new RecipeIngredients(rec2, ing8));
        rec2.getIngredients().add(new RecipeIngredients(rec2, ing9));
        rec2.getIngredients().add(new RecipeIngredients(rec2, ing10));
        rec2.getIngredients().add(new RecipeIngredients(rec2, ing11));
        rec2.getIngredients().add(new RecipeIngredients(rec2, ing12));
        rec2.setRecipeid(102);
        recipeList.add(rec2);

        Recipe rec3 = new Recipe(c3, "Chocolate Mirror Cake", "Grandma Marge", "Add together, bake, eat");
        rec3.getIngredients().add(new RecipeIngredients(rec3, ing13));
        rec3.getIngredients().add(new RecipeIngredients(rec3, ing14));
        rec3.getIngredients().add(new RecipeIngredients(rec3, ing15));
        rec3.getIngredients().add(new RecipeIngredients(rec3, ing16));
        rec3.getIngredients().add(new RecipeIngredients(rec3, ing17));
        rec3.setRecipeid(103);
        recipeList.add(rec3);

//        System.out.println("\n*** Seed Data ***");
//        for (Recipe r : recipeList) {
//            System.out.println(r);
//        }
//        System.out.println("*** Seed Data ***");

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void listAllRecipes() throws Exception {
        String apiUrl = "/recipes/recipes";
        Mockito.when(recipeService.findAll()).thenReturn(recipeList);

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb).andReturn();
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(recipeList);

        System.out.println("Expect: " + er);
        System.out.println("Actual: " + tr);

        assertEquals(er, tr);
    }

    @Test
    public void getRecipeById() {
    }

    @Test
    public void getRecipeLikeName() {
    }

    @Test
    public void addNewRecipe() {
    }

    @Test
    public void updateRecipe() {
    }

    @Test
    public void deleteRecipeById() {
    }
}