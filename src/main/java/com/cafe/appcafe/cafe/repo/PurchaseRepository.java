package com.cafe.appcafe.cafe.repo;

import com.cafe.appcafe.cafe.models.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
