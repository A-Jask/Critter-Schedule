package com.udacity.jdnd.course3.critter.Service;

import com.udacity.jdnd.course3.critter.Repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.Repository.PetRepository;
import com.udacity.jdnd.course3.critter.Entity.Pet;
import com.udacity.jdnd.course3.critter.Entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CustomerRepository customerRepository;


    public List<Pet> getAllPets(){
        return petRepository.findAll();
    }

    public Pet getPetById(Long petId){
        return petRepository.getOne(petId);
    }

    public List<Pet> getPetsByCustomerId(Long customerId){
        return customerRepository.findById(customerId).get().getPets();
    }

    public Pet savePet(Pet pet, Long customerId){
        Customer customer = customerRepository.getOne(customerId);
        List<Pet> pets = new ArrayList<>();
        pet.setCustomer(customer);
        pet = petRepository.save(pet);
        pets.add(pet);
        customer.setPets(pets);
        customerRepository.save(customer);
        return pet;
    }

}
