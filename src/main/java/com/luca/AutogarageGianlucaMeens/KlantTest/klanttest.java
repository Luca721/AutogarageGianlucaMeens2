//package com.luca.AutogarageGianlucaMeens.KlantTest;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.luca.AutogarageGianlucaMeens.Auto.Auto;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.Collection;
//
//@Entity(name = "Klant")
//@Table(name = "Klanten")
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//public class klanttest{
//
//    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    Long id;
//    String voornaam;
//    String achternaam;
//    String adres;
//    String telefoonnummer;
//    String email;
//
//    // Relation between one car and one customer
//    @OneToMany(mappedBy= "eigenaarID")
//    @JsonIgnore
//    private Collection<Auto> autoCollection = new ArrayList<Auto>();
//
//    //Getters and setters
//    public Collection<Auto> getCars() {
//        return autoCollection;
//    }
//
//    public void setCars(Collection<Auto> cars) {
//        this.autoCollection = autoCollection;
//    }
//
//    // Constructors
//    public klanttest(String voornaam, String achternaam, String adres, String telefoonnummer, String email){
//        this.voornaam = voornaam;
//        this.achternaam = achternaam;
//        this.adres = adres;
//        this.telefoonnummer = telefoonnummer;
//        this.email = email;
//    }
//
//}
