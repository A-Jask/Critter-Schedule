package com.udacity.jdnd.course3.critter.Service;

import com.udacity.jdnd.course3.critter.Entity.Customer;
import com.udacity.jdnd.course3.critter.Entity.Pet;
import com.udacity.jdnd.course3.critter.Repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.Repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PetService {

    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerRepository customerRepository;


    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public Pet getPetById(Long petId) {
        return petRepository.getOne(petId);
    }

    public List<Pet> getPetsByCustomerId(Long customerId) {
        return customerRepository.findById(customerId).get().getPets();
    }

    public Pet savePet(Pet pet, Long customerId) {
        Customer customer = customerRepository.getOne(customerId);
        List<Pet> pets = customerRepository.findById(customerId).get().getPets();
        pet.setCustomer(customer);
        pet = petRepository.save(pet);
        pets.add(pet);
        customer.setPets(pets);
        customerRepository.save(customer);
        return pet;
    }

}
