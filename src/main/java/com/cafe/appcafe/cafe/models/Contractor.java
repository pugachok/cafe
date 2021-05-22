package com.cafe.appcafe.cafe.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "contractor")
public class Contractor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long idContractors;

    @Column(name = "name")
    private String nameContractors;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany
    private List<Purchase> purchaseList;

    public Contractor() {
    }

    public Contractor(String nameContractors, String address, String phoneNumber) {
        this.nameContractors = nameContractors;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Long getIdContractors() {
        return idContractors;
    }

    public void setIdContractors(Long idContractors) {
        this.idContractors = idContractors;
    }

    public String getNameContractors() {
        return nameContractors;
    }

    public void setNameContractors(String nameContractors) {
        this.nameContractors = nameContractors;
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

}
