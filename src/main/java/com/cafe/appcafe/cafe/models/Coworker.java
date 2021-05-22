package com.cafe.appcafe.cafe.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "coworker")
//  @SecondaryTable(name = "position")
public class Coworker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long idCoworker;

    @Column(name = "fio")
    private String fio;

    @ManyToOne(optional=false)
    @JoinColumn(name = "id_position")
    private Position position;

    @OneToMany
    private List<Order> orderList;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Coworker() {
    }

    public Coworker(String fio, Position position) {
        this.fio = fio;
        this.position = position;
    }


//    public String getNamePosition() {
//        return position.getNamePosition();
//    }

    public Long getIdCoworker() {
        return idCoworker;
    }

    public void setIdCoworker(Long idCoworker) {
        this.idCoworker = idCoworker;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

}
