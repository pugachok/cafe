package com.cafe.appcafe.cafe.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ingredient")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long idIngredient;

    @Column(name = "name")
    private String nameIngredients;

    @Column(name = "unit")
    private String measure;

    public Ingredient() {
    }

    public Ingredient(String nameIngredients, String measure) {
        this.nameIngredients = nameIngredients;
        this.measure = measure;
    }

    @OneToMany
    private List<IngredientInTheComposition> ingredientInTheCompositionList;

    @OneToMany
    private List<Purchase> purchaseList;

    public Long getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(Long idIngredient) {
        this.idIngredient = idIngredient;
    }

    public String getNameIngredients() {
        return nameIngredients;
    }

    public void setNameIngredients(String nameIngredients) {
        this.nameIngredients = nameIngredients;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }
}
