package com.luca.AutogarageGianlucaMeens.klant;


import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

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
    String telefoon_nummer;

    @Column(name = "Email")
    String email;

    public Klant() {}

    public Klant(Long id, String naam, String achternaam, String telefoonNummer, String email) {
        this.id = id;
        this.naam = naam;
        this.achternaam = achternaam;
        this.telefoon_nummer = telefoonNummer;
        this.email = email;
    }

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
        return telefoon_nummer;
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
        this.telefoon_nummer = telefoonNummer;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Klant))
            return false;
        Klant klant = (Klant) o;
        return Objects.equals(this.id, klant.id) && Objects.equals(this.naam, klant.naam) && Objects.equals(this.achternaam, klant.achternaam)
                && Objects.equals(this.email, klant.email) && Objects.equals(this.telefoon_nummer, klant.telefoon_nummer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.naam, this.achternaam, this.email, this.telefoon_nummer);
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id + ", naam='" + this.naam + " " + this.achternaam + '\'' + ", contact='" + this.email + " "  + this.telefoon_nummer + '\'' + '}';
    }
}

