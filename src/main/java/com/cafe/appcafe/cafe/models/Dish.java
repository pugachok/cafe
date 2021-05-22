package com.cafe.appcafe.cafe.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "dish")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long idDishes;

    @Column(name = "name")
    private String nameDishes;

    @Column(name = "unit")
    private String edIzmereniya;

    @OneToMany
    private List<Menu> menuList;

    @OneToMany
    private List<IngredientInTheComposition> ingredientInTheCompositionList;

    public Dish() {
    }

    public Dish(String nameDishes, String edIzmereniya) {
        this.nameDishes = nameDishes;
        this.edIzmereniya = edIzmereniya;
    }

    public Long getIdDishes() {
        return idDishes;
    }

    public void setIdDishes(Long idDishes) {
        this.idDishes = idDishes;
    }

    public String getNameDishes() {
        return nameDishes;
    }

    public void setNameDishes(String nameDishes) {
        this.nameDishes = nameDishes;
    }

    public String getEdIzmereniya() {
        return edIzmereniya;
    }

    public void setEdIzmereniya(String edIzmereniya) {
        this.edIzmereniya = edIzmereniya;
    }
}
