package com.luca.AutogarageGianlucaMeens.GebruiktOnderdeel;

import com.luca.AutogarageGianlucaMeens.Onderdeel.Onderdeel;
import com.luca.AutogarageGianlucaMeens.Reperatie.Reparatie;

import javax.persistence.*;

@Entity
@Table(name = "GebruikteOnderdelen")
public class GebruiktOnderdeel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "OnderdeelID")
    Onderdeel onderdeelID;

    @ManyToOne
    @JoinColumn(name = "ReperatieID")
    Reparatie reperatieID;

    @Column(name = "Aantal")
    int aantal;

    //setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setOnderdeelID(Onderdeel onderdeelID) {
        this.onderdeelID = onderdeelID;
    }

    public void setReperatieID(Reparatie reperatieID) {
        this.reperatieID = reperatieID;
    }

    public void setAantal(int aantal) {
        this.aantal = aantal;
    }

    //getters
    public Long getId() {
        return id;
    }

    public Onderdeel getOnderdeelID() {
        return onderdeelID;
    }

    public Reparatie getReperatieID() {
        return reperatieID;
    }

    public int getAantal() {
        return aantal;
    }
}
