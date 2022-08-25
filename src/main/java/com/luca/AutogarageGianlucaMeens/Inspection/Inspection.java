package com.luca.AutogarageGianlucaMeens.Inspection;


import com.luca.AutogarageGianlucaMeens.Car.Car;

import javax.persistence.*;

enum RepairAccept {
    accept("1"), decline("2"), awaitingReaction("3");

    private String inspectionCode;
    RepairAccept(String inspectionCode) {
        this.inspectionCode = inspectionCode;
    }

}

@Entity
@Table(name = "Inspection")
public class Inspection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "carID")
    Car carID;

    @Column(name = "damage")
    String damage;

    @Column(name = "expectedPrice")
    Double expectedPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "RepairAccept")
    RepairAccept RepairAccept;

    public Inspection(Long id, String damage, Double expectedPrice) {
        this.id = id;
        this.damage = damage;
        this.expectedPrice = expectedPrice;
    }

    public Inspection() {

    }

    //setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setAutoID(Car carID) {
        this.carID = carID;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public void setExpectedPrice(Double expectedPrice) {
        this.expectedPrice = expectedPrice;
    }

    public void setRepairAccept(RepairAccept repairAccept) {
        RepairAccept = repairAccept;
    }

    //getters
    public Long getId() {
        return id;
    }

    public Car getAutoID() {
        return carID;
    }

    public String getDamage() {
        return damage;
    }

    public Double getExpectedPrice() {
        return expectedPrice;
    }

    public RepairAccept getRepairAccept() {
        return RepairAccept;
    }
}
