package com.cafe.appcafe.cafe.repo;
import com.cafe.appcafe.cafe.models.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {
    Position findByNamePosition(String namePosition);
}
