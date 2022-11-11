package com.udacity.jdnd.course3.critter.Service;

import com.udacity.jdnd.course3.critter.Entity.Customer;
import com.udacity.jdnd.course3.critter.Entity.Pet;
import com.udacity.jdnd.course3.critter.Repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.Repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PetRepository petRepository;


    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getOwnerByPetId(long petId) {
        return petRepository.getOne(petId).getCustomer();
    }

    public Customer saveCustomer(Customer customer, List<Long> petId) {
        List<Pet> petList = new ArrayList<>();
        if (petId != null && !petId.isEmpty() ) {
            petList = petId.stream().map((petIDS) -> petRepository.getOne(petIDS)).collect(Collectors.toList());
        }
        customer.setPets(petList);
        return customerRepository.save(customer);
    }

}