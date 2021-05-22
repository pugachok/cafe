package com.cafe.appcafe.cafe.repo;

import com.cafe.appcafe.cafe.models.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Long> {
    Dish findByNameDishes(String name);
}
