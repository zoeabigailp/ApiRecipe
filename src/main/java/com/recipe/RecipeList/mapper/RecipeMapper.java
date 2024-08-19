package com.recipe.RecipeList.mapper;

import com.recipe.RecipeList.dto.RecipeDTO;
import com.recipe.RecipeList.model.RecipeModel;

import java.util.ArrayList;
import java.util.List;

public class RecipeMapper {

    //pasaje de RecipeDTO a RecipeModel
    public static RecipeModel DtoToModel(RecipeDTO recipeDTO){
        RecipeModel recipeModel = new RecipeModel(recipeDTO.getTitle(),
                recipeDTO.getDescription(), recipeDTO.getDuration(), recipeDTO.getCategory());
        return recipeModel;
    }

    //pasaje de RecipeModel a RecipeDTO
    public static RecipeDTO ModelToDto (RecipeModel recipeModel){
        RecipeDTO recipeDTO = new RecipeDTO(recipeModel.getTitle(),
                recipeModel.getDescription(), recipeModel.getDuration(), recipeModel.getCategory());
        return  recipeDTO;
    }

    //pasaje de RecipeDTO a RecipeModel LIST
    public static List<RecipeModel> DtoToModelList(List<RecipeDTO> recipeDTO){
        List<RecipeModel> recipeModel = new ArrayList<>();
        for(RecipeDTO itemsDTO : recipeDTO){
           recipeModel.add(DtoToModel(itemsDTO));
        }
        return  recipeModel;
    }

    //pasaje de RecipeModel a RecipeDTO LIST
    public  static  List<RecipeDTO> ModelToDtoList(List<RecipeModel> recipeModels){
        List<RecipeDTO> recipeDTO = new ArrayList<>();
        for(RecipeModel itemsModel : recipeModels){
            recipeDTO.add(ModelToDto(itemsModel));
        }
        return recipeDTO;
    }
}
