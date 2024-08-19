package com.recipe.RecipeList.service;

import com.recipe.RecipeList.dto.RecipeDTO;
import com.recipe.RecipeList.exception.RecipeDataIntegrityException;
import com.recipe.RecipeList.mapper.RecipeMapper;
import com.recipe.RecipeList.model.RecipeModel;
import com.recipe.RecipeList.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    @Autowired
    private  RecipeRepository recipeRepository;


    public RecipeDTO CreateRecipe(RecipeDTO recipe) throws RecipeDataIntegrityException, NullPointerException, NumberFormatException {
        RecipeModel recipeModel = RecipeMapper.DtoToModel(recipe);

        try {
            if(recipe.getTitle().isEmpty()) throw new  NullPointerException("No se permiten campos vacios! ");
            recipeRepository.save(recipeModel);

        } catch (DataIntegrityViolationException e) {
            throw new RecipeDataIntegrityException("Data integrity violation " + e.getMessage());
        }
        return recipe;
    }

    public List<RecipeDTO> AllRecipe(){
       return RecipeMapper.ModelToDtoList(recipeRepository.findAll());
    }

    public RecipeDTO IdRecipe(long id) throws NullPointerException, NumberFormatException{
        if(!recipeRepository.existsById(id)) throw new NullPointerException("Not found");
        return RecipeMapper.ModelToDto(recipeRepository.findById(id).orElseThrow());
    }

    public RecipeDTO ModifyRecipe (long id, RecipeDTO recipeDTO) throws NullPointerException, NumberFormatException{
        RecipeModel recipeModelGet = recipeRepository.findById(id).orElseThrow();

        recipeModelGet.setTitle(recipeDTO.getTitle());
        recipeModelGet.setDescription(recipeDTO.getDescription());
        recipeModelGet.setDuration(recipeDTO.getDuration());
        recipeModelGet.setCategory(recipeDTO.getCategory());

        return RecipeMapper.ModelToDto(recipeRepository.save(recipeModelGet));
    }

    public List<RecipeDTO> SearchRecipe(String category){
        return RecipeMapper.ModelToDtoList(recipeRepository.findBycategory(category));
    }

    public List<String> AllCategory(){
        return recipeRepository.findDistincByCategory();
    }
}
