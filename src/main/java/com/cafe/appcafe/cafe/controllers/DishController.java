package com.cafe.appcafe.cafe.controllers;

import com.cafe.appcafe.cafe.models.Dish;
import com.cafe.appcafe.cafe.service.DishService;
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
public class DishController {

    private final DishService dishService;

    @Autowired
    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping("/dish")
    @PreAuthorize("hasAuthority('developers:read')")
    public String findAll(Model model) {
        List<Dish> dishes = dishService.findAll();
        model.addAttribute("dish", dishes);
        return "dish/dish-list";
    }

    @GetMapping("/dish-create")
    @PreAuthorize("hasAuthority('developers:write')")
    public String createDishForm() {
        return "dish/dish-create";
    }

    @PostMapping("/dish-create")
    @PreAuthorize("hasAuthority('developers:write')")
    public String createDish(@RequestParam String nameDish,
                             @RequestParam String edIzmereniya,
                             Model model) {
        dishService.saveDish(nameDish, edIzmereniya);
        return "redirect:/dish";
    }

    @GetMapping("/dish-delete/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String dishDelete(@PathVariable(value = "id") Long id) {
        dishService.delete(id);
        return "redirect:/dish";
    }

    @GetMapping("/dish-update/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String updateDishForm(@PathVariable(value = "id") Long id, Model model) {
        if (dishService.dishList(id).isEmpty()) {
            return "redirect:/dish";
        }
        model.addAttribute("dish", dishService.dishList(id));
        return "dish/dish-update";
    }

    @PostMapping("/dish-update/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String updateDish(@PathVariable(value = "id") long id,
                             @RequestParam String nameDish,
                             @RequestParam String edIzmereniya) {
        dishService.update(id, nameDish, edIzmereniya);
        return "redirect:/dish";
    }
}
