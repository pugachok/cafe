package com.cafe.appcafe.cafe.service;

import com.cafe.appcafe.cafe.models.Contractor;
import com.cafe.appcafe.cafe.models.Ingredient;
import com.cafe.appcafe.cafe.models.Purchase;
import com.cafe.appcafe.cafe.repo.ContractorRepository;
import com.cafe.appcafe.cafe.repo.IngredientRepository;
import com.cafe.appcafe.cafe.repo.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    private  final PurchaseRepository purchaseRepository;

    private final IngredientRepository ingredientRepository;

    private final ContractorRepository contractorRepository;

    @Autowired
    public PurchaseService(PurchaseRepository purchaseRepository, IngredientRepository ingredientRepository, ContractorRepository contractorRepository) {
        this.purchaseRepository = purchaseRepository;
        this.ingredientRepository = ingredientRepository;
        this.contractorRepository = contractorRepository;
    }

    public List<Purchase> findAllPurchase() {
        return purchaseRepository.findAll();
    }

    public List<Ingredient> findAllIngredient() {
        return ingredientRepository.findAll();
    }

    public List<Contractor> findAllContractor() {
        return contractorRepository.findAll();
    }

    public Purchase save(String nameIngredient, int count, String nameContractor, String date, Double price) {
        Ingredient ingredient = ingredientRepository.findByNameIngredients(nameIngredient);
        Contractor contractor = contractorRepository.findByNameContractors(nameContractor);
        return purchaseRepository.save(new Purchase(ingredient, count, contractor, date, price));
    }

    public List<Purchase> purchaseList(Long id) {
        Optional<Purchase> purchase = purchaseRepository.findById(id);
        ArrayList<Purchase> res = new ArrayList<>();
        purchase.ifPresent(res::add);
        return res;
    }

    public Purchase update(Long id, String nameIngredient, int count, String nameContractor, String date, Double price) {
        Purchase purchase = purchaseRepository.findById(id).orElseThrow();
        Ingredient ingredient = ingredientRepository.findByNameIngredients(nameIngredient);
        Contractor contractor = contractorRepository.findByNameContractors(nameContractor);
        purchase.setIngredient(ingredient);
        purchase.setAmount(count);
        purchase.setContractor(contractor);
        purchase.setDate(date);
        purchase.setPrice(price);
        return purchaseRepository.save(purchase);
    }

    public void delete(Long id) {
        purchaseRepository.deleteById(id);
    }
}