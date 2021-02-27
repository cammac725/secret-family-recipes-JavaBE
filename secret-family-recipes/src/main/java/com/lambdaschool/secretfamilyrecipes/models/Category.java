package com.lambdaschool.secretfamilyrecipes.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "categories")
public class Category extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long categoryid;

    @NotNull
    @Column(unique = true)
    private String name;

    public Category() {
    }

    public Category(@NotNull String name) {
        this.name = name;
    }

    public long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(long categoryid) {
        this.categoryid = categoryid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
