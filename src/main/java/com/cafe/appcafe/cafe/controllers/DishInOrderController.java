package com.cafe.appcafe.cafe.controllers;

import com.cafe.appcafe.cafe.models.DishInOrder;
import com.cafe.appcafe.cafe.models.Menu;
import com.cafe.appcafe.cafe.service.DishInOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class DishInOrderController {

    private final DishInOrderService dishInOrderService;

    @Autowired
    public DishInOrderController(DishInOrderService dishInOrderService) {
        this.dishInOrderService = dishInOrderService;
    }

    @GetMapping("/dish-in-order")
    @PreAuthorize("hasAuthority('developers:write')")
    public String findAll(Model model) {
        List<DishInOrder> dishInOrderList = dishInOrderService.findAllDishInOrder();
        model.addAttribute("dishInOrder", dishInOrderList);
        return "dish-in-order/dish-in-order-list";
    }

    @GetMapping("/actual-dish-in-order")
    @PreAuthorize("hasAuthority('developers:read')")
    public String findAllActualDishInOrder(Model model) {
        List<DishInOrder> dishInOrderList = dishInOrderService.findAllActualDishInOrder(LocalDate.now().toString());
        model.addAttribute("dishInOrder", dishInOrderList);
        return "dish-in-order/actual-dish-in-order-list";
    }

    @GetMapping("/dish-in-order-create")
    @PreAuthorize("hasAuthority('developers:write')")
    public String createForm(Model model) {
        List<Menu> menus = dishInOrderService.findAllMenus();
        model.addAttribute("menu", menus);
        return "dish-in-order/dish-in-order-create";
    }

    @PostMapping("/dish-in-order-create")
    @PreAuthorize("hasAuthority('developers:write')")
    public String create(@RequestParam String arrayNameDish,
                         @RequestParam String address,
                         @RequestParam String phoneNumber,
                         @RequestParam String date,
                         @RequestParam int count) {
        String[] getNameDishByDate = arrayNameDish.split("\\|");
        dishInOrderService.save(getNameDishByDate[0], getNameDishByDate[1], address, phoneNumber, date, count);
        return "redirect:/dish-in-order";
    }

    @GetMapping("/dish-in-order-update/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String updateForm(@PathVariable(value = "id") Long id, Model model) {
        List<Menu> menus = dishInOrderService.findAllMenus();
        if (dishInOrderService.dishInOrderList(id).isEmpty()) {
            return "redirect:/dish-in-order";
        }
        model.addAttribute("menu", menus);
        model.addAttribute("dishInOrder", dishInOrderService.dishInOrderList(id));
        return "dish-in-order/dish-in-order-update";
    }

    @PostMapping("/dish-in-order-update/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String update(@PathVariable(value = "id") Long id,
                         @RequestParam String arrayNameDish,
                         @RequestParam String address,
                         @RequestParam String phoneNumber,
                         @RequestParam String date,
                         @RequestParam int count) {
        String[] getNameDishByDate = arrayNameDish.split("\\|");
        dishInOrderService.update(id, getNameDishByDate[0], getNameDishByDate[1], address, phoneNumber, date, count);
        return "redirect:/dish-in-order";
    }

    @GetMapping("/dish-in-order-delete/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String delete(@PathVariable(value = "id") Long id) {
        dishInOrderService.delete(id);
        return "redirect:/dish-in-order";
    }

}
