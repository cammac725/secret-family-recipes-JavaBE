package com.lambdaschool.secretfamilyrecipes;

import com.lambdaschool.secretfamilyrecipes.models.*;
import com.lambdaschool.secretfamilyrecipes.services.CategoryService;
import com.lambdaschool.secretfamilyrecipes.services.RecipeService;
import com.lambdaschool.secretfamilyrecipes.services.RoleService;
import com.lambdaschool.secretfamilyrecipes.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * SeedData puts both known and random data into the database. It implements CommandLineRunner.
 * <p>
 * CoomandLineRunner: Spring Boot automatically runs the run method once and only once
 * after the application context has been loaded.
 */
@Transactional
@Component
public class SeedData
    implements CommandLineRunner
{
    /**
     * Connects the Role Service to this process
     */
    @Autowired
    RoleService roleService;

    /**
     * Connects the user service to this process
     */
    @Autowired
    UserService userService;

    /**
     * Connects the category service to this process
     */
    @Autowired
    CategoryService categoryService;

    @Autowired
    RecipeService recipeService;

    /**
     * Generates test, seed data for our application
     * First a set of known data is seeded into our database.
     * Second a random set of data using Java Faker is seeded into our database.
     * Note this process does not remove data from the database. So if data exists in the database
     * prior to running this process, that data remains in the database.
     *
     * @param args The parameter is required by the parent interface but is not used in this process.
     */
    @Transactional
    @Override
    public void run(String[] args) throws
                                   Exception
    {
//        userService.deleteAll();
//        roleService.deleteAll();
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


//        categoryService.deleteAll();
        Category c1 = new Category("entree");
        Category c2 = new Category("appetizer");
        Category c3 = new Category("dessert");
        Category c4 = new Category("soup");
        Category c5 = new Category("vegan");

        c1 = categoryService.save(c1);
        c2 = categoryService.save(c2);
        c3 = categoryService.save(c3);
        c4 = categoryService.save(c4);
        c5 = categoryService.save(c5);

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

        Recipe rec1 = new Recipe(c1, "Shrimp tacos", "Cousin Juan", "Add together, eat.");
        rec1 = recipeService.save(rec1);
        Recipe rec2 = new Recipe(c5, "Broccoli Casserole", "Barb", "Add together, bake, eat");
        Recipe rec3 = new Recipe(c3, "Chocolate Mirror Cake", "Grandma Marge", "Add together, bake, eat");
        Recipe rec4 = new Recipe(c1, "Lasagna", "Aunt Mary", "Add together, bake, eat");
        Recipe rec5 = new Recipe(c4, "Barley Soup", "Judy", "Add together, simmer, eat");
        Recipe rec6 = new Recipe(c2, "Stuffed Mushrooms", "PCC", "Add together, bake, eat");

    }
}