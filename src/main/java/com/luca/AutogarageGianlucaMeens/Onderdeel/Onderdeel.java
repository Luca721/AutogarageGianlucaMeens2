package com.luca.AutogarageGianlucaMeens.Onderdeel;

import javax.persistence.*;

@Entity
@Table(name = "Onderdelen")
public class Onderdeel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "Naam")
    String naam;

    @Column(name = "Inkoopprijs")
    double inkoopprijs;

    @Column(name = "VerkoopPrijsExclBTW")
    double verkoopprijs;

    @Column(name = "Voorraad")
    int voorraad;

    //setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setInkoopprijs(double inkoopprijs) {
        this.inkoopprijs = inkoopprijs;
    }

    public void setVerkoopprijs(double verkoopprijs) {
        this.verkoopprijs = verkoopprijs;
    }

    public void setVoorraad(int voorraad) {
        this.voorraad = voorraad;
    }

    // getters
    public Long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public double getInkoopprijs() {
        return inkoopprijs;
    }

    public double getVerkoopprijs() {
        return verkoopprijs;
    }

    public int getVoorraad() {
        return voorraad;
    }
}
