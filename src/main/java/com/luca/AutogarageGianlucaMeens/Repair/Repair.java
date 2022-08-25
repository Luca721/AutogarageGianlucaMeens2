package com.luca.AutogarageGianlucaMeens.Repair;

import javax.persistence.*;

@Entity
@Table(name = "Repairs")
public class Repair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "damage")
    String damage;

    @Column(name = "costs")
    double costs;

    @Column(name = "price")
    double price;

    public Repair(long id, String damage, double costs, double price) {
        this.id = id;
        this.damage = damage;
        this.costs = costs;
        this.price = price;
    }

    public Repair() {

    }

    //setters
    public void setId(long id) {
        this.id = id;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public void setCosts(double costs) {
        this.costs = costs;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    //getters
    public long getId() {
        return id;
    }

    public String getDamage() {
        return damage;
    }

    public double getCosts() {
        return costs;
    }

    public double getPrice() {
        return price;
    }
}

