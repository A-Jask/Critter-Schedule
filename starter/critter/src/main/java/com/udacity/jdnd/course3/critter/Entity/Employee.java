package com.udacity.jdnd.course3.critter.Entity;

import com.udacity.jdnd.course3.critter.Enums.EmployeeSkill;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.Set;


@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ElementCollection
    private Set<EmployeeSkill> employeeSkills;

    @ElementCollection
    private Set<DayOfWeek> availability;

    public Employee() {
    }

    public Employee(Long id, String name, Set<EmployeeSkill> employeeSkills, Set<DayOfWeek> availability) {
        this.id = id;
        this.name = name;
        this.employeeSkills = employeeSkills;
        this.availability = availability;
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

    public Set<EmployeeSkill> getSkills() {
        return employeeSkills;
    }

    public void setSkills(Set<EmployeeSkill> employeeSkills) {
        this.employeeSkills = employeeSkills;
    }

    public Set<DayOfWeek> getAvailability() {
        return availability;
    }

    public void setAvailability(Set<DayOfWeek> availability) {
        this.availability = availability;
    }
}