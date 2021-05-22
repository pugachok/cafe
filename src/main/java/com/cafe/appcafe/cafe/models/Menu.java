package com.cafe.appcafe.cafe.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long idMenu;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_dish")
    private Dish dish;

    @Column(name = "price_dish")
    private double price;

    @Column(name = "data")
    private String date;

    @OneToMany
    private List<Order> orderList;

    @OneToMany
    private List<DishInOrder> dishInOrderList;

    public Menu() {
    }

    public Menu(Dish dish, double price, String date) {
        this.dish = dish;
        this.price = price;
        this.date = date;
    }

    public Long getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Long idMenu) {
        this.idMenu = idMenu;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
