package com.recipe.RecipeList.service;

import com.recipe.RecipeList.dto.RecipeDTO;
import com.recipe.RecipeList.exception.RecipeDataIntegrityException;
import com.recipe.RecipeList.mapper.RecipeMapper;
import com.recipe.RecipeList.model.RecipeModel;
import com.recipe.RecipeList.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RecipeService {

    @Autowired
    private  RecipeRepository recipeRepository;


    public RecipeDTO CreateRecipe(RecipeDTO recipe) throws RecipeDataIntegrityException, NullPointerException {
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
        return RecipeMapper.ModelToDto(recipeRepository.findById(id).orElseThrow(
                ()->  new NullPointerException("Not found")
        ));
    }

    public RecipeDTO ModifyRecipe (long id, RecipeDTO recipeDTO) throws NullPointerException, NumberFormatException{
        RecipeModel recipeModelGet = recipeRepository.findById(id).orElseThrow(
                () -> new NullPointerException("Not found")
        );

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

    public Map<String, Object> DeleteRecipe(long id){
        Map<String, Object> messenge = new HashMap<>();
        messenge.put("Messenge", "Delete recipe");
        recipeRepository.deleteById(id);
        return messenge;
    }

    public List<RecipeDTO> FilterByDuration(Integer max, Integer min){
        if(max==null){
            return RecipeMapper.ModelToDtoList(recipeRepository.filterByDurationMin(min));
        }
        if(max<1 && min<1) throw new NumberFormatException("Error Format");
        return RecipeMapper.ModelToDtoList(recipeRepository.filterByDuration(max,min));
    }
}
