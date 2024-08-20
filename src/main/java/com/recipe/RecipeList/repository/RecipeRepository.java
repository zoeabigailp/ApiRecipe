package com.recipe.RecipeList.repository;

import com.recipe.RecipeList.dto.RecipeDTO;
import com.recipe.RecipeList.model.RecipeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeRepository extends JpaRepository<RecipeModel, Long> {
    List<RecipeModel> findBycategory(String category);
    @Query(value = "SELECT DISTINCT category FROM recipe", nativeQuery = true)
    List<String> findDistincByCategory();

    @Query(value = "SELECT * FROM recipe WHERE duration < :max AND duration > :min", nativeQuery = true)
    List<RecipeModel> filterByDuration(@Param("max") int max, @Param("min") int min);

    @Query(value = "SELECT * FROM recipe WHERE duration > :min", nativeQuery = true)
    List<RecipeModel> filterByDurationMin (@Param("min") int min);
}
