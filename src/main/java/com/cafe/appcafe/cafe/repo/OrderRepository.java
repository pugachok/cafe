package com.cafe.appcafe.cafe.repo;

import com.cafe.appcafe.cafe.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findOrdersByDateOrder(String date);
}
