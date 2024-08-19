package com.recipe.RecipeList.controller;

import com.recipe.RecipeList.exception.RecipeDataIntegrityException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RecipeHandler {

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String HandlerNullPointerException(){
        return "Not found, empty data";
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String HandlerNumberFormatException(){
        return "Format incorrect";
    }

    @ExceptionHandler(RecipeDataIntegrityException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String HandlerRecipeDataIntegrityViolationException(){
        return "Integrity data violation";
    }
}
