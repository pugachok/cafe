package com.cafe.appcafe.cafe.service;

import com.cafe.appcafe.cafe.models.Position;
import com.cafe.appcafe.cafe.repo.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PositionService {

    private final PositionRepository positionRepository;

    public Position savePosition(String namePosition) {
        return save(new Position(namePosition));
    }

    public Position updatePosition(Long id, String namePosition) {
        Position position = findById(id);
        position.setNamePosition(namePosition);
        return save(position);
    }

    public List<Position> positionList(long id) {
        Optional<Position> pos = positionRepository.findById(id);
        ArrayList<Position> res = new ArrayList<>();
        pos.ifPresent(res::add);
        return res;
    }

    @Autowired
    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public Position findById(Long id) {
        return positionRepository.getOne(id);
    }

    public List<Position> findAll() {
        return positionRepository.findAll();
    }

    public Position save(Position position) {
        return positionRepository.save(position);
    }

    public void delete(Long id) {
        positionRepository.deleteById(id);
    }
}
