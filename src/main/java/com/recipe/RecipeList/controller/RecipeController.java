package com.recipe.RecipeList.controller;

import com.recipe.RecipeList.dto.RecipeDTO;
import com.recipe.RecipeList.service.RecipeService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @PostMapping("/create")
    public ResponseEntity<RecipeDTO> CreateRecipe(@RequestBody RecipeDTO recipe){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(recipeService.CreateRecipe(recipe));
    }

    @GetMapping("/all")
    public List<RecipeDTO> AllRecipe(){
        return recipeService.AllRecipe();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDTO> IdRecipe(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(recipeService.IdRecipe(id));
    }

    @PostMapping("/modify")
    public ResponseEntity<RecipeDTO> ModifyRecipe(@PathParam("id") long id, @RequestBody RecipeDTO recipeDTO){
        return ResponseEntity.status(HttpStatus.OK).body(recipeService.ModifyRecipe(id, recipeDTO));
    }

    @GetMapping("search/{category}")
    public List<RecipeDTO> SearchRecipe(@PathVariable String category){
        return recipeService.SearchRecipe(category);
    }

    @GetMapping("/category")
    public List<String> AllCategory(){
        return recipeService.AllCategory();
    }
}
