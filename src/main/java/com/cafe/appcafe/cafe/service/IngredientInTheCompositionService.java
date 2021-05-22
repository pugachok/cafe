package com.cafe.appcafe.cafe.service;

import com.cafe.appcafe.cafe.models.Dish;
import com.cafe.appcafe.cafe.models.Ingredient;
import com.cafe.appcafe.cafe.models.IngredientInTheComposition;
import com.cafe.appcafe.cafe.repo.DishRepository;
import com.cafe.appcafe.cafe.repo.IngredientInTheCompositionRepository;
import com.cafe.appcafe.cafe.repo.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IngredientInTheCompositionService {

    private final IngredientInTheCompositionRepository ingredientInTheCompositionRepository;

    private final DishRepository dishRepository;

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientInTheCompositionService(IngredientInTheCompositionRepository ingredientInTheCompositionRepository, IngredientRepository ingredientRepository, DishRepository dishRepository) {
        this.ingredientInTheCompositionRepository = ingredientInTheCompositionRepository;
        this.ingredientRepository = ingredientRepository;
        this.dishRepository = dishRepository;
    }

    public List<IngredientInTheComposition> findAllIngredientInTheComposition() {
        return ingredientInTheCompositionRepository.findAll();
    }

    public List<Dish> findAllDish() {
        return dishRepository.findAll();
    }

    public List<Ingredient> findAllIngredient() {
        return ingredientRepository.findAll();
    }

    public IngredientInTheComposition save(String nameIngredient, String nameDish, int count) {
        Ingredient ingredient = ingredientRepository.findByNameIngredients(nameIngredient);
        Dish dish = dishRepository.findByNameDishes(nameDish);
        return ingredientInTheCompositionRepository.save(new IngredientInTheComposition(ingredient, dish, count));
    }

    public List<IngredientInTheComposition> ingredientInTheCompositionList(Long id) {
        Optional<IngredientInTheComposition> ingredientInTheComposition = ingredientInTheCompositionRepository.findById(id);
        ArrayList<IngredientInTheComposition> res = new ArrayList<>();
        ingredientInTheComposition.ifPresent(res::add);
        return res;
    }

    public IngredientInTheComposition update(Long id, String nameIngredient, String nameDish, int count) {
        IngredientInTheComposition ingredientInTheComposition = ingredientInTheCompositionRepository.findById(id).orElseThrow();
        Ingredient ingredient = ingredientRepository.findByNameIngredients(nameIngredient);
        Dish dish = dishRepository.findByNameDishes(nameDish);
        ingredientInTheComposition.setIngredient(ingredient);
        ingredientInTheComposition.setDish(dish);
        ingredientInTheComposition.setAmount(count);
        return ingredientInTheCompositionRepository.save(ingredientInTheComposition);
    }

    public void delete(Long id) {
        ingredientInTheCompositionRepository.deleteById(id);
    }


}
