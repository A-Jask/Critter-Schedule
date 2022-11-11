package com.udacity.jdnd.course3.critter.Service;

import com.udacity.jdnd.course3.critter.Entity.Employee;
import com.udacity.jdnd.course3.critter.Enums.EmployeeSkill;
import com.udacity.jdnd.course3.critter.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long employeeId) {
        return employeeRepository.getOne(employeeId);
    }

    public Employee saveEmployee(Employee employeeId) {
        return employeeRepository.save(employeeId);
    }

    public void setEmployeeAvailability(Set<DayOfWeek> daysAvailable, long employeeID) {
        Employee employee = employeeRepository.getOne(employeeID);
        employee.setAvailability(daysAvailable);
        employeeRepository.save(employee);
    }

    public List<Employee> getEmployeeByDateAndSkill(LocalDate localDate, Set<EmployeeSkill> skillSet) {
        return employeeRepository
                .findEmployeeByAvailability(localDate.getDayOfWeek())
                .stream()
                .filter(employee -> employee.getSkills().containsAll(skillSet))
                .collect(Collectors.toList());
    }

}
