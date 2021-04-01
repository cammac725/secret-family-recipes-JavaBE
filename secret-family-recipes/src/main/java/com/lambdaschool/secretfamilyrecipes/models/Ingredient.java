package com.lambdaschool.secretfamilyrecipes.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ingredients")
public class Ingredient extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ingredientid;

    @NotNull
    @Column(unique = true)
    private String ingredientname;

    @OneToMany(mappedBy = "ingredient",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JsonIgnoreProperties(value = "ingredient", allowSetters = true)
    private Set<RecipeIngredients> recipes = new HashSet<>();

    public Ingredient() {
    }

    public Ingredient(@NotNull String ingredientname) {
        this.ingredientname = ingredientname;
    }

    public long getIngredientid() {
        return ingredientid;
    }

    public void setIngredientid(long ingredientid) {
        this.ingredientid = ingredientid;
    }

    public String getName() {
        return ingredientname;
    }

    public void setName(String ingredientname) {
        this.ingredientname = ingredientname;
    }

    public Set<RecipeIngredients> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<RecipeIngredients> recipes) {
        this.recipes = recipes;
    }
}
