package com.cafe.appcafe.cafe.repo;

import com.cafe.appcafe.cafe.models.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Menu findMenuByDish_NameDishesAndDate(String nameDish, String date);

    List<Menu> findMenusByDate(String date);
}
