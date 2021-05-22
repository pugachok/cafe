package com.cafe.appcafe.cafe.service;

import com.cafe.appcafe.cafe.models.Dish;
import com.cafe.appcafe.cafe.repo.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DishService {

    private final DishRepository dishRepository;

    @Autowired
    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public Dish save(Dish dish) {
        return dishRepository.save(dish);
    }

    public void delete(Long id) {
        dishRepository.deleteById(id);
    }

    public List<Dish> findAll() {
        return dishRepository.findAll();
    }

    public Dish findById(Long id) {
        return dishRepository.getOne(id);
    }

    public Dish saveDish(String name, String edIzmereniya) {
        return save(new Dish(name, edIzmereniya));
    }

    public Dish update(Long id, String nameDish, String edIzmereniya) {
        Dish dish = findById(id);
        dish.setNameDishes(nameDish);
        dish.setEdIzmereniya(edIzmereniya);
        return save(dish);
    }

    public List<Dish> dishList(long id) {
        Optional<Dish> dish = dishRepository.findById(id);
        ArrayList<Dish> res = new ArrayList<>();
        dish.ifPresent(res::add);
        return res;
    }



}
