package com.luca.AutogarageGianlucaMeens.Car;

import com.luca.AutogarageGianlucaMeens.AutoPapieren.CarPapers;
import com.luca.AutogarageGianlucaMeens.klant.Customer;

import javax.persistence.*;

@Entity
@Table(name = "Car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    @JoinColumn(name = "ownerID")
    Customer ownerID;

    @Column(name = "licensePlate")
    String licensePlate;

    @Column(name = "brand")
    String brand;

    @Column(name = "Model")
    String model;

    @Column(name = "versionYear")
    int versionYear;

    @OneToOne
    @JoinColumn(name = "carPapers")
    CarPapers carPapers;

    //setters
    public void setId(long id) {
        this.id = id;
    }

    public void setOwnerID(Customer ownerID) {
        this.ownerID = ownerID;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setVersionYear(int versionYear) {
        this.versionYear = versionYear;
    }

    public void setCarPapers(CarPapers carPapers) {
        this.carPapers = carPapers;
    }

    //getters
    public long getId() {
        return id;
    }

    public Customer getOwnerID() {
        return ownerID;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getVersionYear() {
        return versionYear;
    }

    public CarPapers getCarPapers() { return carPapers; }

}