package com.cafe.appcafe.cafe.repo;

import com.cafe.appcafe.cafe.models.DishInOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishInOrderRepository extends JpaRepository<DishInOrder, Long> {
    List<DishInOrder> findDishInOrderByDate(String date);
}
