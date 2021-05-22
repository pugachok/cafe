package com.cafe.appcafe.cafe.models;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "position")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long idPosition;

    @Column(name = "name_position")
    private String namePosition;

    public Position() {
    }

    public Position(String namePosition) {
        this.namePosition = namePosition;
    }

    @OneToMany
    private List<Coworker> coworkerList;

    public Long getIdPosition() {
        return idPosition;
    }

    public void setIdPosition(Long idPosition) {
        this.idPosition = idPosition;
    }

    public String getNamePosition() {
        return namePosition;
    }

    public void setNamePosition(String namePosition) {
        this.namePosition = namePosition;
    }
}
