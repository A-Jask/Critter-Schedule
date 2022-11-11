package com.udacity.jdnd.course3.critter.Service;

import com.udacity.jdnd.course3.critter.Entity.Pet;
import com.udacity.jdnd.course3.critter.Entity.Schedule;
import com.udacity.jdnd.course3.critter.Repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.Repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.Repository.PetRepository;
import com.udacity.jdnd.course3.critter.Repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PetRepository petRepository;

    @Autowired
    EmployeeRepository employeeRepository;


    public List<Schedule> getAllSchedule() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> getScheduleByPetId(Long petID) {
        return scheduleRepository.getScheduleByPets(petRepository.getOne(petID));
    }

    public List<Schedule> getScheduleByEmployeeId(Long employeeID) {
        return scheduleRepository.getAllScheduleByEmployee(employeeRepository.getOne(employeeID));
    }

    public List<Schedule> getCustomerSchedule(Long customerID) {
        List<Pet> petList = customerRepository.getOne(customerID).getPets();
        List<Schedule> scheduleList = new LinkedList<>();
        petList.forEach(pet -> {
            List<Schedule> tempSchedule = scheduleRepository.getScheduleByPets(pet);
            scheduleList.addAll(tempSchedule);
        });
        return scheduleList;
    }

    public Schedule saveSchedule(Schedule schedule, List<Long> employeeID, List<Long> petID) {
        schedule.setEmployee(employeeRepository.findAllById(employeeID));
        schedule.setPets(petRepository.findAllById(petID));
        return scheduleRepository.save(schedule);
    }
}