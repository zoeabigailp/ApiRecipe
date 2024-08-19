package com.recipe.RecipeList.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class RecipeDTO {
    private String title;
    private String description;
    private int duration;
    private String category;
}
