package com.cafe.appcafe.cafe.repo;

import com.cafe.appcafe.cafe.models.Contractor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractorRepository extends JpaRepository<Contractor, Long> {
    Contractor findByNameContractors(String name);
}
