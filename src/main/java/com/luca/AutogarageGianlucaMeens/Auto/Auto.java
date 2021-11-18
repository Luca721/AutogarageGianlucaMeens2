package com.luca.AutogarageGianlucaMeens.Auto;

import com.luca.AutogarageGianlucaMeens.klant.Klant;

import javax.persistence.*;

@Entity
@Table(name = "Autos")
public class Auto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @ManyToOne
    @JoinColumn(name = "EigenaarID")
    Klant eigenaarID;

    @Column(name = "Kenteken")
    String Kenteken;

    @Column(name = "Merk")
    String merk;

    @Column(name = "Model")
    String model;

    @Column(name = "Bouwjaar")
    int bouwjaar;

    //setters
    public void setId(long id) {
        this.id = id;
    }

    public void setEigenaarID(Klant eigenaarID) {
        this.eigenaarID = eigenaarID;
    }

    public void setKenteken(String kenteken) {
        Kenteken = kenteken;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setBouwjaar(int bouwjaar) {
        this.bouwjaar = bouwjaar;
    }

    //getters
    public long getId() {
        return id;
    }

    public Klant getEigenaarID() {
        return eigenaarID;
    }

    public String getKenteken() {
        return Kenteken;
    }

    public String getMerk() {
        return merk;
    }

    public String getModel() {
        return model;
    }

    public int getBouwjaar() {
        return bouwjaar;
    }
}
