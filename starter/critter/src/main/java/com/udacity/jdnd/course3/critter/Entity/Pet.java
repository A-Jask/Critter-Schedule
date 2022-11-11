package com.udacity.jdnd.course3.critter.Entity;

import com.udacity.jdnd.course3.critter.Enums.PetType;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Nationalized
    private String name;

    @ManyToOne(targetEntity = Customer.class)
    private Customer customer;

    private PetType petType;
    private LocalDate birthDate;
    private String notes;

    public Pet() {
    }

    public Pet(PetType type, String name, LocalDate birthDate, String notes) {
        this.petType = type;
        this.name = name;
        this.birthDate = birthDate;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PetType getType() {
        return petType;
    }

    public void setType(PetType type) {
        this.petType = type;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getDate() {
        return birthDate;
    }

    public void setDate(LocalDate date) {
        this.birthDate = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}