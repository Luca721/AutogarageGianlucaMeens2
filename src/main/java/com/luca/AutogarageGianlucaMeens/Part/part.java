package com.luca.AutogarageGianlucaMeens.Part;

import javax.persistence.*;

@Entity
@Table(name = "parts")
public class part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "cost")
    double cost;

    @Column(name = "salesPrice")
    double salesprice;

    @Column(name = "stock")
    int stock;

    //setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setSalesprice(double salesprice) {
        this.salesprice = salesprice;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public double getSalesprice() {
        return salesprice;
    }

    public int getStock() {
        return stock;
    }
}
