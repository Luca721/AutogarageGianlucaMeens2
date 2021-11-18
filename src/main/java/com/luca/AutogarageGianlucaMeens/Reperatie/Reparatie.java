package com.luca.AutogarageGianlucaMeens.Reperatie;

import javax.persistence.*;

@Entity
@Table(name = "Autos")
public class Reparatie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Column(name = "Schade")
    String schade;

    @Column(name = "Kosten")
    double kosten;

    @Column(name = "PrijsExclBTW")
    double prijs;

    //setters
    public void setId(long id) {
        this.id = id;
    }

    public void setSchade(String schade) {
        this.schade = schade;
    }

    public void setKosten(double kosten) {
        this.kosten = kosten;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    //getters
    public long getId() {
        return id;
    }

    public String getSchade() {
        return schade;
    }

    public double getKosten() {
        return kosten;
    }

    public double getPrijs() {
        return prijs;
    }
}

