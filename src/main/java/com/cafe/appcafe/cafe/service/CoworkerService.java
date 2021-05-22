package com.cafe.appcafe.cafe.service;

import com.cafe.appcafe.cafe.models.Coworker;
import com.cafe.appcafe.cafe.models.Position;
import com.cafe.appcafe.cafe.repo.CoworkerRepository;
import com.cafe.appcafe.cafe.repo.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CoworkerService { // будет дергать репозиторные методы

    private final CoworkerRepository coworkerRepository;

    private final PositionRepository positionRepository;

    @Autowired
    public CoworkerService(CoworkerRepository coworkerRepository, PositionRepository positionRepository) {
        this.coworkerRepository = coworkerRepository;
        this.positionRepository = positionRepository;
    }

    public Coworker findById(Long id) {
        return coworkerRepository.getOne(id);
    }

    public List<Position> getPositionList() {
        return positionRepository.findAll();
    }


    public List<Coworker> findAll() {
        return coworkerRepository.findAll();
    }

    public Coworker saveCoworker(String fio, String namePosition) {
        Position position = positionRepository.findByNamePosition(namePosition);
        return coworkerRepository.save(new Coworker(fio, position));
    }

    public Coworker updateCoworker(Long id, String fio, String namePosition) {
        Coworker coworker = coworkerRepository.findById(id).orElseThrow();
        Position position = positionRepository.findByNamePosition(namePosition);
        coworker.setFio(fio);
        coworker.setPosition(position);
        return coworkerRepository.save(coworker);
    }

    public List<Coworker> coworkerList(long id) {
        Optional<Coworker> coworker = coworkerRepository.findById(id);
        ArrayList<Coworker> res = new ArrayList<>();
        coworker.ifPresent(res::add);
        return res;
    }

    public void deleteCoworker(Long id) {
        coworkerRepository.deleteById(id);
    }
}
