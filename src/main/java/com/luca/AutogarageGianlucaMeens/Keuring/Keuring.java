package com.luca.AutogarageGianlucaMeens.Keuring;


import com.luca.AutogarageGianlucaMeens.Auto.Auto;

import javax.persistence.*;

enum ReperatieGoedkeuring{
    Goedgekeurt, afgekeurt, geenReactie
}

@Entity
@Table(name = "keuringen")
public class Keuring {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    @JoinColumn(name = "AutoID")
    Auto autoID;

    @Column(name = "Schade")
    String schade;

    @Column(name = "VerwachtePrijs")
    Double verwachtePrijs;

    @Enumerated(EnumType.STRING)
    @Column(name = "ReperatieGoedkeuring")
    ReperatieGoedkeuring ReperatieGoedkeuring;

    //setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setAutoID(Auto autoID) {
        this.autoID = autoID;
    }

    public void setSchade(String schade) {
        this.schade = schade;
    }

    public void setVerwachtePrijs(Double verwachtePrijs) {
        this.verwachtePrijs = verwachtePrijs;
    }

    public void setReperatieGoedkeuring(com.luca.AutogarageGianlucaMeens.Keuring.ReperatieGoedkeuring reperatieGoedkeuring) {
        ReperatieGoedkeuring = reperatieGoedkeuring;
    }

    //getters
    public Long getId() {
        return id;
    }

    public Auto getAutoID() {
        return autoID;
    }

    public String getSchade() {
        return schade;
    }

    public Double getVerwachtePrijs() {
        return verwachtePrijs;
    }

    public com.luca.AutogarageGianlucaMeens.Keuring.ReperatieGoedkeuring getReperatieGoedkeuring() {
        return ReperatieGoedkeuring;
    }
}
