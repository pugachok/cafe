package com.cafe.appcafe.cafe.service;

import com.cafe.appcafe.cafe.models.DishInOrder;
import com.cafe.appcafe.cafe.models.Menu;
import com.cafe.appcafe.cafe.repo.DishInOrderRepository;
import com.cafe.appcafe.cafe.repo.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DishInOrderService {

    private final DishInOrderRepository dishInOrderRepository;

    private final MenuRepository menuRepository;

    @Autowired
    public DishInOrderService(DishInOrderRepository dishInOrderRepository, MenuRepository menuRepository) {
        this.dishInOrderRepository = dishInOrderRepository;
        this.menuRepository = menuRepository;
    }

    public List<DishInOrder> findAllDishInOrder() {
        return dishInOrderRepository.findAll();
    }

    public List<Menu> findAllMenus() {
        return menuRepository.findAll();
    }

    public List<DishInOrder> dishInOrderList(Long id) {
        Optional<DishInOrder> dishInOrder = dishInOrderRepository.findById(id);
        ArrayList<DishInOrder> res = new ArrayList<>();
        dishInOrder.ifPresent(res::add);
        return res;
    }

    public DishInOrder save(String nameDish, String dateMenu, String address, String phoneNumber, String date, int count) {
        Menu menu = menuRepository.findMenuByDish_NameDishesAndDate(nameDish, dateMenu);
        return dishInOrderRepository.save(new DishInOrder(menu, address, phoneNumber, date, count));
    }

    public DishInOrder update(Long id, String nameDish, String dateMenu, String address, String phoneNumber, String date, int count) {
        DishInOrder dishInOrder = dishInOrderRepository.findById(id).orElseThrow();
        Menu menu = menuRepository.findMenuByDish_NameDishesAndDate(nameDish, dateMenu);
        dishInOrder.setMenu(menu);
        dishInOrder.setAddress(address);
        dishInOrder.setPhoneNumber(phoneNumber);
        dishInOrder.setDate(date);
        dishInOrder.setAmount(count);
        return dishInOrderRepository.save(dishInOrder);
    }

    public void delete(Long id) {
        dishInOrderRepository.deleteById(id);
    }
}
