package com.cafe.appcafe.cafe.service;

import com.cafe.appcafe.cafe.models.Coworker;
import com.cafe.appcafe.cafe.models.Menu;
import com.cafe.appcafe.cafe.models.Order;
import com.cafe.appcafe.cafe.repo.CoworkerRepository;
import com.cafe.appcafe.cafe.repo.MenuRepository;
import com.cafe.appcafe.cafe.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final CoworkerRepository coworkerRepository;

    private final MenuRepository menuRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, CoworkerRepository coworkerRepository, MenuRepository menuRepository) {
        this.orderRepository = orderRepository;
        this.coworkerRepository = coworkerRepository;
        this.menuRepository = menuRepository;
    }

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    public List<Coworker> findAllCoworkers() {
        return coworkerRepository.findAll();
    }

    public List<Menu> findAllMenus() {
        return menuRepository.findAll();
    }

    public List<Order> findAllActualOrder(String date) {
        return orderRepository.findOrdersByDateOrder(date);
    }

    public Order save(String nameCoworker, String nameDish, String dateMenu, String date, String time, int numberTable, int count) {
        Coworker coworker = coworkerRepository.findByFio(nameCoworker);
        Menu menu = menuRepository.findMenuByDish_NameDishesAndDate(nameDish, dateMenu);
        return orderRepository.save(new Order(coworker, menu, date, time, numberTable, count));
    }

    public List<Order> orderList(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        ArrayList<Order> res = new ArrayList<>();
        order.ifPresent(res::add);
        return res;
    }

    public Order update(Long id, String nameCoworker, String nameDish, String dateMenu, String date, String time, int numberTable, int count) {
        Order order = orderRepository.findById(id).orElseThrow();
        Coworker coworker = coworkerRepository.findByFio(nameCoworker);
        Menu menu = menuRepository.findMenuByDish_NameDishesAndDate(nameDish, dateMenu);
        order.setCoworker(coworker);
        order.setMenu(menu);
        order.setDateOrder(date);
        order.setTime(time);
        order.setTableNumber(numberTable);
        order.setAmount(count);
        return orderRepository.save(order);
    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}
