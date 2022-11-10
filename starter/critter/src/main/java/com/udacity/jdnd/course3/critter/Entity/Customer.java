package com.udacity.jdnd.course3.critter.Entity;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@NamedQueries({
//        @NamedQuery(
//                name = "customer.getAll",
//                query = "select c from Customer c"),
//        @NamedQuery(
//                name = "customer.getByPet",
//                query = "select c from Customer c join c.pets p where p.id = :id")
//})


@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Nationalized
    private String name;

    private String phoneNumber;
    private String notes;

    @OneToMany(targetEntity = Pet.class)
    List<Pet> petList;


    public Customer() {
    }

    public Customer(Long id, String name, String phoneNumber, String notes) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.notes = notes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Pet> getPets() {
        return petList;
    }

    public void setPets(List<Pet> pets) {
        this.petList = pets;
    }

    public void addPet(Pet pet){
        petList.add(pet);
    }



}
