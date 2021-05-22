package com.cafe.appcafe.cafe.repo;

import com.cafe.appcafe.cafe.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Ingredient findByNameIngredients(String nameIngredient);
}
