package com.lambdaschool.secretfamilyrecipes.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "recipes")
public class Recipe extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long recipeid;

    @NotNull
    @Column(unique = true)
    private String name;

    @Column(unique = false)
    private String source;

    @Column
    private String instructions;

    @Column
    private String ingredients;

    public Recipe() {
    }

    public Recipe(@NotNull String name, String source, String instructions, String ingredients) {
        this.name = name;
        this.source = source;
        this.instructions = instructions;
        this.ingredients = ingredients;
    }

    public long getRecipeid() {
        return recipeid;
    }

    public void setRecipeid(long recipeid) {
        this.recipeid = recipeid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
}
