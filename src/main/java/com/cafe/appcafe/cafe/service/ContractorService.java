package com.cafe.appcafe.cafe.service;

import com.cafe.appcafe.cafe.models.Contractor;
import com.cafe.appcafe.cafe.repo.ContractorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContractorService {

    private final ContractorRepository contractorRepository;

    @Autowired
    public ContractorService(ContractorRepository contractorRepository) {
        this.contractorRepository = contractorRepository;
    }

    public List<Contractor> findAll() {
        return contractorRepository.findAll();
    }

    public Contractor save(String nameContractor, String address, String phoneNumber) {
        return contractorRepository.save(new Contractor(nameContractor, address, phoneNumber));
    }

    public List<Contractor> contractorList(Long id) {
        Optional<Contractor> contractor = contractorRepository.findById(id);
        ArrayList<Contractor> res = new ArrayList<>();
        contractor.ifPresent(res::add);
        return res;
    }

    public Contractor update(Long id, String nameContractor, String address, String phoneNumber) {
        Contractor contractor = contractorRepository.findById(id).orElseThrow();
        contractor.setNameContractors(nameContractor);
        contractor.setAddress(address);
        contractor.setPhoneNumber(phoneNumber);
        return contractorRepository.save(contractor);
    }

    public void delete(Long id) {
        contractorRepository.deleteById(id);
    }
}
