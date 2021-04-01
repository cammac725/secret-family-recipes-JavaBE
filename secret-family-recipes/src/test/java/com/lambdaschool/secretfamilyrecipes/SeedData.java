package com.lambdaschool.secretfamilyrecipes;

import com.lambdaschool.secretfamilyrecipes.models.*;
import com.lambdaschool.secretfamilyrecipes.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class SeedData
        implements CommandLineRunner
{

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    RecipeService recipeService;

    @Autowired
    IngredientService ingredientService;

    @Transactional
    @Override
    public void run(String[] args) throws Exception
    {
//        userService.deleteAll();
//        roleService.deleteAll();
//        categoryService.deleteAll();
//        recipeService.deleteAll();
//        ingredientService.deleteAll();

        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");

        r1 = roleService.save(r1);
        r2 = roleService.save(r2);
        r3 = roleService.save(r3);

        // admin, data, user
        User u1 = new User("admin",
                "password",
                "admin@lambdaschool.local");
        u1.getRoles().add(new UserRoles(u1, r1));
        u1.getRoles().add(new UserRoles(u1, r2));
        u1.getRoles().add(new UserRoles(u1, r3));
        u1.getUseremails().add(new Useremail(u1, "admin@email.local"));
        u1.getUseremails().add(new Useremail(u1, "admin@mymail.local"));
        userService.save(u1);

        // data, user
        User u2 = new User("user",
                "user",
                "user@lambdaschool.local");
        u2.getRoles().add(new UserRoles(u2, r2));
        u2.getRoles().add(new UserRoles(u2, r3));
        userService.save(u2);

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
        Ingredient ing13 = new Ingredient("noodles");
        Ingredient ing14 = new Ingredient("ricotta cheese");
        Ingredient ing15 = new Ingredient("parmesan cheese");
        Ingredient ing16 = new Ingredient("bolognese sauce");
        Ingredient ing17 = new Ingredient("cup of flour");
        Ingredient ing18 = new Ingredient("baking powder");
        Ingredient ing19 = new Ingredient("cocoa");
        Ingredient ing20 = new Ingredient("melted chocolate");
        Ingredient ing21 = new Ingredient("eggs");
        Ingredient ing22 = new Ingredient("cooked barley");
        Ingredient ing23 = new Ingredient("chopped vegies");
        Ingredient ing24 = new Ingredient("vegie broth");
        Ingredient ing25 = new Ingredient("minced garlic");
        Ingredient ing26 = new Ingredient("large mushroom caps");
        Ingredient ing27 = new Ingredient("dungeness crab meat");
        Ingredient ing28 = new Ingredient("butter");
        Ingredient ing29 = new Ingredient("minced onion");
        Ingredient ing30 = new Ingredient("chopped parsley");

        ing1 = ingredientService.save(ing1);
        ing2 = ingredientService.save(ing2);
        ing3 = ingredientService.save(ing3);
        ing4 = ingredientService.save(ing4);
        ing5 = ingredientService.save(ing5);
        ing6 = ingredientService.save(ing6);
        ing7 = ingredientService.save(ing7);
        ing8 = ingredientService.save(ing8);
        ing9 = ingredientService.save(ing9);
        ing10 = ingredientService.save(ing10);
        ing11 = ingredientService.save(ing11);
        ing12 = ingredientService.save(ing12);
        ing13 = ingredientService.save(ing13);
        ing14 = ingredientService.save(ing14);
        ing15 = ingredientService.save(ing15);
        ing16 = ingredientService.save(ing16);
        ing17 = ingredientService.save(ing17);
        ing18 = ingredientService.save(ing18);
        ing19 = ingredientService.save(ing19);
        ing20 = ingredientService.save(ing20);
        ing21 = ingredientService.save(ing21);
        ing22 = ingredientService.save(ing22);
        ing23 = ingredientService.save(ing23);
        ing24 = ingredientService.save(ing24);
        ing25 = ingredientService.save(ing25);
        ing26 = ingredientService.save(ing26);
        ing27 = ingredientService.save(ing27);
        ing28 = ingredientService.save(ing28);
        ing29 = ingredientService.save(ing29);
        ing30 = ingredientService.save(ing30);

        Category c1 = new Category("entree");
        Category c2 = new Category("appetizer");
        Category c3 = new Category("dessert");
        Category c4 = new Category("soup");
        Category c5 = new Category("vegan");

        Recipe rec1 = new Recipe(c1,"test-tacos", "Cousin Juan", "Add together, eat.");
        rec1.getIngredients().add(new RecipeIngredients(rec1, ing1));
        rec1.getIngredients().add(new RecipeIngredients(rec1, ing2));
        rec1.getIngredients().add(new RecipeIngredients(rec1, ing3));
        rec1.getIngredients().add(new RecipeIngredients(rec1, ing4));
        rec1.getIngredients().add(new RecipeIngredients(rec1, ing5));
        rec1.getIngredients().add(new RecipeIngredients(rec1, ing6));

        Recipe rec2 = new Recipe(c5, "Broccoli Casserole", "Barb", "Add together, bake, eat");
        rec2.getIngredients().add(new RecipeIngredients(rec2, ing7));
        rec2.getIngredients().add(new RecipeIngredients(rec2, ing8));
        rec2.getIngredients().add(new RecipeIngredients(rec2, ing9));
        rec2.getIngredients().add(new RecipeIngredients(rec2, ing10));
        rec2.getIngredients().add(new RecipeIngredients(rec2, ing11));
        rec2.getIngredients().add(new RecipeIngredients(rec2, ing12));

        Recipe rec3 = new Recipe(c3, "Chocolate Mirror Cake", "Grandma Marge", "Add together, bake, eat");
        rec3.getIngredients().add(new RecipeIngredients(rec3, ing17));
        rec3.getIngredients().add(new RecipeIngredients(rec3, ing18));
        rec3.getIngredients().add(new RecipeIngredients(rec3, ing19));
        rec3.getIngredients().add(new RecipeIngredients(rec3, ing20));
        rec3.getIngredients().add(new RecipeIngredients(rec3, ing21));

        Recipe rec4 = new Recipe(c1, "Lasagna", "Aunt Mary", "Add together, bake, eat");
        rec4.getIngredients().add(new RecipeIngredients(rec4, ing13));
        rec4.getIngredients().add(new RecipeIngredients(rec4, ing14));
        rec4.getIngredients().add(new RecipeIngredients(rec4, ing15));
        rec4.getIngredients().add(new RecipeIngredients(rec4, ing16));
        rec4.getIngredients().add(new RecipeIngredients(rec4, ing3));

        Recipe rec5 = new Recipe(c4, "Barley Soup", "Judy", "Add together, simmer, eat");
        rec5.getIngredients().add(new RecipeIngredients(rec5, ing22));
        rec5.getIngredients().add(new RecipeIngredients(rec5, ing23));
        rec5.getIngredients().add(new RecipeIngredients(rec5, ing24));
        rec5.getIngredients().add(new RecipeIngredients(rec5, ing25));

        Recipe rec6 = new Recipe(c2, "Stuffed Mushrooms", "PCC", "Add together, bake, eat");
        rec6.getIngredients().add(new RecipeIngredients(rec6, ing26));
        rec6.getIngredients().add(new RecipeIngredients(rec6, ing27));
        rec6.getIngredients().add(new RecipeIngredients(rec6, ing28));
        rec6.getIngredients().add(new RecipeIngredients(rec6, ing29));
        rec6.getIngredients().add(new RecipeIngredients(rec6, ing30));

        c1.getRecipes().add(rec1);
        c1.getRecipes().add(rec4);
        c2.getRecipes().add(rec6);
        c3.getRecipes().add(rec3);
        c4.getRecipes().add(rec5);
        c5.getRecipes().add(rec2);

        categoryService.save(c1);
        categoryService.save(c2);
        categoryService.save(c3);
        categoryService.save(c4);
        categoryService.save(c5);

    }
}