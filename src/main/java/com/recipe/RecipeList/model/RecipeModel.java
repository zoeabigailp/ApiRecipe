package com.recipe.RecipeList.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "recipe")
public class RecipeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 20)
    private String title;

    @Column(nullable = false, length = 250)
    private String description;

    @Column
    private int duration;

    @Column
    private String category;

    //constructor sin id
    public RecipeModel(String title, String description, int duration, String category) {
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.category = category;
    }
}
