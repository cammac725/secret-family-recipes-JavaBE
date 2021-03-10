package com.lambdaschool.secretfamilyrecipes.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "recipes")
public class Recipe extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long recipeid;

    @NotNull
    @Column(unique = true)
    private String name;

    @Column
    private String source;

    @Column
    private String instructions;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "categoryid")
    @JsonIgnoreProperties(value = "recipes", allowSetters = true)
    private Category category;

    @OneToMany(mappedBy = "recipe",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JsonIgnoreProperties(value = "recipe", allowSetters = true)
    private Set<RecipeIngredients> ingredients = new HashSet<>();

    public Recipe() {
    }

    public Recipe(@NotNull Category category,  String name, String source, String instructions) {
        this.category = category;
        this.name = name;
        this.source = source;
        this.instructions = instructions;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<RecipeIngredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<RecipeIngredients> ingredients) {
        this.ingredients = ingredients;
    }
}
