package com.cafe.appcafe.cafe.models;

import javax.persistence.*;

@Entity
@Table(name = "dishes_in_order")
public class DishInOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long idDishes;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_menu")
    private Menu menu;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "data")
    private String date;

    @Column(name = "sum")
    private int amount;

    public DishInOrder() {
    }

    public DishInOrder(Menu menu, String address, String phoneNumber, String date, int amount) {
        this.menu = menu;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.amount = amount;
    }

    public Long getIdDishes() {
        return idDishes;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
