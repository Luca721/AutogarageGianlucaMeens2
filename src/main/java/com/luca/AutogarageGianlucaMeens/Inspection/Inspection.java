package com.luca.AutogarageGianlucaMeens.Inspection;


import com.luca.AutogarageGianlucaMeens.Car.Car;

import javax.persistence.*;

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
    String RepairAccept;

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

    public void setRepairAccept(String repairAccept) {
        this.RepairAccept = repairAccept;
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

    public String getRepairAccept() {
        return RepairAccept;
    }
}
