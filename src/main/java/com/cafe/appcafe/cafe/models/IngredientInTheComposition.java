package com.cafe.appcafe.cafe.models;

import javax.persistence.*;

@Entity
@Table(name = "ingredients_in_the_composition")
public class IngredientInTheComposition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long idIngredientInTheComposition;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_ingredient")
    private Ingredient ingredient;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_dish")
    private Dish dish;

    @Column(name = "sum")
    private int amount;

    public IngredientInTheComposition() {
    }

    public IngredientInTheComposition(Ingredient ingredient, Dish dish, int amount) {
        this.ingredient = ingredient;
        this.dish = dish;
        this.amount = amount;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Long getIdIngredientInTheComposition() {
        return idIngredientInTheComposition;
    }

    public void setIdIngredientInTheComposition(Long idIngredientInTheComposition) {
        this.idIngredientInTheComposition = idIngredientInTheComposition;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
