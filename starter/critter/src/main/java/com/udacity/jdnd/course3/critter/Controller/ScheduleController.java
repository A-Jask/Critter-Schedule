package com.udacity.jdnd.course3.critter.Controller;

import com.udacity.jdnd.course3.critter.DTO.ScheduleDTO;
import com.udacity.jdnd.course3.critter.Entity.Schedule;
import com.udacity.jdnd.course3.critter.Service.ScheduleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;


    private ScheduleDTO convertScheduleToScheduleDTO(Schedule schedule) {
        List<Long> employeeIds = schedule.getEmployee().stream().map(employee -> employee.getId()).collect(Collectors.toList());
        List<Long> petIds = schedule.getPets().stream().map(pet -> pet.getId()).collect(Collectors.toList());
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);
        scheduleDTO.setId(schedule.getId());
        scheduleDTO.setEmployeeIds(employeeIds);
        scheduleDTO.setPetIds(petIds);
        return new ScheduleDTO(schedule.getId(), employeeIds, petIds, schedule.getDate(), schedule.getEmployeeSkill());
    }


    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule(scheduleDTO.getDate(), scheduleDTO.getActivities());
        return convertScheduleToScheduleDTO(scheduleService
                .saveSchedule(schedule, scheduleDTO.getEmployeeIds(), scheduleDTO.getPetIds()));
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return scheduleService.getAllSchedule()
                .stream()
                .map(this::convertScheduleToScheduleDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        return scheduleService.getScheduleByPetId(petId)
                .stream()
                .map(this::convertScheduleToScheduleDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return scheduleService.getScheduleByEmployeeId(employeeId)
                .stream()
                .map(this::convertScheduleToScheduleDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        return scheduleService.getCustomerSchedule(customerId)
                .stream()
                .map(this::convertScheduleToScheduleDTO)
                .collect(Collectors.toList());
    }
}
