package com.cafe.appcafe.cafe.service;

import com.cafe.appcafe.cafe.models.Ingredient;
import com.cafe.appcafe.cafe.repo.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }

    public Ingredient save(String nameIngredient, String measure) {
        return ingredientRepository.save(new Ingredient(nameIngredient, measure));
    }

    public List<Ingredient> ingredientList(long id) {
        Optional<Ingredient> ingredient = ingredientRepository.findById(id);
        ArrayList<Ingredient> res = new ArrayList<>();
        ingredient.ifPresent(res::add);
        return res;
    }

    public Ingredient update(Long id, String nameIngredient, String measure) {
        Ingredient ingredient = ingredientRepository.findById(id).orElseThrow();
        ingredient.setNameIngredients(nameIngredient);
        ingredient.setMeasure(measure);
        return ingredientRepository.save(ingredient);
    }

    public void delete(Long id) {
        ingredientRepository.deleteById(id);
    }
}
