package com.cafe.appcafe.cafe.service;

import com.cafe.appcafe.cafe.models.Dish;
import com.cafe.appcafe.cafe.models.Menu;
import com.cafe.appcafe.cafe.repo.DishRepository;
import com.cafe.appcafe.cafe.repo.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    private final DishRepository dishRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository, DishRepository dishRepository) {
        this.menuRepository = menuRepository;
        this.dishRepository = dishRepository;
    }

    public List<Menu> findAll() {
        return menuRepository.findAll();
    }

    public List<Dish> findAllDishes() {
        return dishRepository.findAll();
    }

    public Menu save(String nameDish, double price, String date) {
        Dish dish = dishRepository.findByNameDishes(nameDish);
        return menuRepository.save(new Menu(dish, price, date));
    }

    public void delete(Long id) {
        menuRepository.deleteById(id);
    }

    public List<Menu> menuList(long id) {
        Optional<Menu> menu = menuRepository.findById(id);
        ArrayList<Menu> res = new ArrayList<>();
        menu.ifPresent(res::add);
        return res;
    }

    public Menu update(long id, String nameDish, double price, String data) {
        Menu menu = menuRepository.findById(id).orElseThrow();
        Dish dish = dishRepository.findByNameDishes(nameDish);
        menu.setDish(dish);
        menu.setPrice(price);
        menu.setDate(data);
        return menuRepository.save(menu);
    }
}
