package com.cafe.appcafe.cafe.models;

import javax.persistence.*;

@Entity
@Table(name = "purchase")
public class Purchase { //Закупка

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long idPurchase;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_ingredient")
    private Ingredient ingredient;

    @Column(name = "sum")
    private int amount;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_contractors")
    private Contractor contractor;

    @Column(name = "data")
    private String date;

    @Column(name = "price")
    private double price;

    public Purchase() {
    }

    public Purchase(Ingredient ingredient, int amount, Contractor contractor, String date, double price) {
        this.ingredient = ingredient;
        this.amount = amount;
        this.contractor = contractor;
        this.date = date;
        this.price = price;
    }

    public Long getIdPurchase() {
        return idPurchase;
    }

    public void setIdPurchase(Long idPurchase) {
        this.idPurchase = idPurchase;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
