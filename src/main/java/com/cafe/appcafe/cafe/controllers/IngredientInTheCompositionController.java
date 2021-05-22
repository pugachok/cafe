package com.cafe.appcafe.cafe.controllers;

import com.cafe.appcafe.cafe.models.Dish;
import com.cafe.appcafe.cafe.models.Ingredient;
import com.cafe.appcafe.cafe.models.IngredientInTheComposition;
import com.cafe.appcafe.cafe.service.IngredientInTheCompositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IngredientInTheCompositionController {

    private final IngredientInTheCompositionService ingredientInTheCompositionService;

    @Autowired
    public IngredientInTheCompositionController(IngredientInTheCompositionService ingredientInTheCompositionService) {
        this.ingredientInTheCompositionService = ingredientInTheCompositionService;
    }

    @GetMapping("/ingredient-in-the-composition")
    @PreAuthorize("hasAuthority('developers:read')")
    public String findAll(Model model) {
        List<IngredientInTheComposition> ingredientInTheCompositionList = ingredientInTheCompositionService.findAllIngredientInTheComposition();
        model.addAttribute("ingredientComposition", ingredientInTheCompositionList);
        return "ingredient-in-the-composition/ingredient-in-the-composition-list";
    }

    @GetMapping("/ingredient-in-the-composition-create")
    @PreAuthorize("hasAuthority('developers:write')")
    public String createForm(Model model) {
        List<Ingredient> ingredients = ingredientInTheCompositionService.findAllIngredient();
        List<Dish> dishes = ingredientInTheCompositionService.findAllDish();
        model.addAttribute("ingredient", ingredients);
        model.addAttribute("dish", dishes);
        return "ingredient-in-the-composition/ingredient-in-the-composition-create";
    }

    @PostMapping("/ingredient-in-the-composition-create")
    @PreAuthorize("hasAuthority('developers:write')")
    public String create(@RequestParam String nameIngredient,
                         @RequestParam String nameDish,
                         @RequestParam int count) {
        ingredientInTheCompositionService.save(nameIngredient, nameDish, count);
        return "redirect:/ingredient-in-the-composition";
    }

    @GetMapping("/ingredient-in-the-composition-update/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String updateForm(@PathVariable(value = "id") Long id, Model model) {
        List<Ingredient> ingredients = ingredientInTheCompositionService.findAllIngredient();
        List<Dish> dishes = ingredientInTheCompositionService.findAllDish();

        if (ingredientInTheCompositionService.ingredientInTheCompositionList(id).isEmpty()) {
            return "redirect:/ingredient-in-the-composition";
        }
        model.addAttribute("ingredient", ingredients);
        model.addAttribute("dish", dishes);
        model.addAttribute("ingredientComposition", ingredientInTheCompositionService.ingredientInTheCompositionList(id));
        return "ingredient-in-the-composition/ingredient-in-the-composition-update";
    }

    @PostMapping("/ingredient-in-the-composition-update/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String update(@PathVariable(value = "id") Long id,
                         @RequestParam String nameIngredient,
                         @RequestParam String nameDish,
                         @RequestParam int count) {
        ingredientInTheCompositionService.update(id, nameIngredient, nameDish, count);
        return "redirect:/ingredient-in-the-composition";
    }

    @GetMapping("/ingredient-in-the-composition-delete/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String delete(@PathVariable(value = "id") Long id) {
        ingredientInTheCompositionService.delete(id);
        return "redirect:/ingredient-in-the-composition";
    }
}
