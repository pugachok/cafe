package com.cafe.appcafe.cafe.repo;

import com.cafe.appcafe.cafe.models.Coworker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoworkerRepository extends JpaRepository<Coworker, Long> {
    Coworker findByFio(String fio);
}
