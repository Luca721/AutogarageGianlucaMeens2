package com.luca.AutogarageGianlucaMeens.klant;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "lastName")
    String lastName;

    @Column(name = "phoneNumber")
    String phoneNumber;

    @Column(name = "Email")
    String email;

    public Customer() {}

    public Customer(Long id, String name, String lastName, String phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    //getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    //setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Customer))
            return false;
        Customer customer = (Customer) o;
        return Objects.equals(this.id, customer.id) && Objects.equals(this.name, customer.name) && Objects.equals(this.lastName, customer.lastName)
                && Objects.equals(this.email, customer.email) && Objects.equals(this.phoneNumber, customer.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.lastName, this.email, this.phoneNumber);
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id + ", naam='" + this.name + " " + this.lastName + '\'' + ", contact='" + this.email + " "  + this.phoneNumber + '\'' + '}';
    }
}

