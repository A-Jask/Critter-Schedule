package com.udacity.jdnd.course3.critter.Service;

import com.udacity.jdnd.course3.critter.Entity.Pet;
import com.udacity.jdnd.course3.critter.Repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.Entity.Customer;
import com.udacity.jdnd.course3.critter.Repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PetRepository petRepository;


    public List<Customer> getAllOwners(){
        return customerRepository.findAll();
    }

    public Customer getOwnerByPetId(long petId){
        return petRepository.getOne(petId).getOwner();
    }

    public Customer saveCustomer (Customer customer, List<Long> petId) {
        List<Pet> petList = new ArrayList<>();
        if(petId.isEmpty() && petId != null){
            petList = petId.stream().map((petIDS) -> petRepository.getOne(petIDS)).collect(Collectors.toList());
        }
        customer.setPets(petList);
        return customerRepository.save(customer);
    }

}