package com.udacity.jdnd.course3.critter.Service;

import com.udacity.jdnd.course3.critter.Enums.EmployeeSkill;
import com.udacity.jdnd.course3.critter.Repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.Entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    public Employee getEmployeeById(Long employeeId) {
        return employeeRepository.getOne(employeeId);
    }

    public Employee saveEmployee(Employee employeeId) {
        return employeeRepository.save(employeeId);
    }

    public void setEmployeeAvailability(Set<DayOfWeek> daysAvailable, long employeeID){
        Employee employee = employeeRepository.getOne(employeeID);
        employee.setDaysAvailable(daysAvailable);
        employeeRepository.save(employee);
    }

    public List<Employee> getEmployeeByDateAndSkill(LocalDate localDate, Set<EmployeeSkill> skillSet){
        return employeeRepository
                .getAllByDaysAvailable(localDate.getDayOfWeek())
                .stream()
                .filter(employee -> employee.getSkills().containsAll(skillSet))
                .collect(Collectors.toList());
    }

}
