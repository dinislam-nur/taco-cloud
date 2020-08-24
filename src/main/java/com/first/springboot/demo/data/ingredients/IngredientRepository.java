package com.first.springboot.demo.data.ingredients;

import com.first.springboot.demo.domains.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
