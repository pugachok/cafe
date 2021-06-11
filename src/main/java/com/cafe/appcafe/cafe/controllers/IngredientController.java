package com.cafe.appcafe.cafe.controllers;

import com.cafe.appcafe.cafe.models.Ingredient;
import com.cafe.appcafe.cafe.service.IngredientService;
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
public class IngredientController {

    private final IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/ingredient")
    @PreAuthorize("hasAuthority('developers:write')")
    public String findAll(Model model) {
        List<Ingredient> ingredientList = ingredientService.findAll();
        model.addAttribute("ingredient", ingredientList);
        return "ingredient/ingredient-list";
    }

    @GetMapping("/ingredient-create")
    @PreAuthorize("hasAuthority('developers:write')")
    public String createForm() {
        return "/ingredient/ingredient-create";
    }

    @PostMapping("/ingredient-create")
    public String create(@RequestParam String nameIngredient,
                         @RequestParam String measure) {
        ingredientService.save(nameIngredient, measure);
        return "redirect:/ingredient";
    }

    @GetMapping("/ingredient-update/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String updateForm(@PathVariable(value = "id") Long id, Model model) {
        if (ingredientService.ingredientList(id).isEmpty()) {
            return "redirect:/ingredient";
        }
        model.addAttribute("ingredient", ingredientService.ingredientList(id));
        return "ingredient/ingredient-update";
    }

    @PostMapping("/ingredient-update/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String update(@PathVariable(value = "id") Long id,
                         @RequestParam String nameIngredient,
                         @RequestParam String measure) {
        ingredientService.update(id, nameIngredient, measure);
        return "redirect:/ingredient";
    }

    @GetMapping(("/ingredient-delete/{id}"))
    @PreAuthorize("hasAuthority('developers:write')")
    public String delete(@PathVariable(value = "id") Long id) {
        ingredientService.delete(id);
        return "redirect:/ingredient";
    }

}
