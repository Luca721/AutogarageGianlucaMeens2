package com.luca.AutogarageGianlucaMeens.klant;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "klanten")
public class Klant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "Naam")
    String naam;

    @Column(name = "Achternaam")
    String achternaam;

    @Column(name = "TelefoonNummer")
    String telefoonNummer;

    @Column(name = "Email")
    String email;

    //getters
    public Long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public String getTelefoonNummer() {
        return telefoonNummer;
    }

    public String getEmail() {
        return email;
    }

    //setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public void setTelefoonNummer(String telefoonNummer) {
        this.telefoonNummer = telefoonNummer;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

