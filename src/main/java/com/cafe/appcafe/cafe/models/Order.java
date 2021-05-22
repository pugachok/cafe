package com.cafe.appcafe.cafe.models;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long idOrder;


    @ManyToOne(optional = false)
    @JoinColumn(name = "id_coworker")
    private Coworker coworker;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_menu")
    private Menu menu;

    @Column(name = "data")
    private String dateOrder;

    @Column(name = "time")
    private String time;

    @Column(name = "table_number")
    private int tableNumber;

    @Column(name = "sum")
    private int amount;

    public Order() {
    }

    public Order(Coworker coworker, Menu menu, String dateOrder, String time, int tableNumber, int amount) {
        this.coworker = coworker;
        this.menu = menu;
        this.dateOrder = dateOrder;
        this.time = time;
        this.tableNumber = tableNumber;
        this.amount = amount;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public Coworker getCoworker() {
        return coworker;
    }

    public void setCoworker(Coworker coworker) {
        this.coworker = coworker;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
