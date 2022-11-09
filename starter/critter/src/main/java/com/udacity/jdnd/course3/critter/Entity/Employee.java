package com.udacity.jdnd.course3.critter.Entity;

import com.udacity.jdnd.course3.critter.Enums.EmployeeSkill;
import org.hibernate.annotations.Nationalized;
import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.Set;


@NamedQueries({
        @NamedQuery(
                name = "employee.getAll",
                query = "SELECT c  Employee c"),

        @NamedQuery(
                name = "employee.bySkill",
                query = "SELECT e FROM Employee e JOIN e.skills s WHERE s IN :skills"),

        @NamedQuery(
                name = "employee.byDayAvailable",
                query = "SELECT e FROM Employee e JOIN e.daysAvailable d WHERE :day = d"),

        @NamedQuery(
                name = "employee.bySkillAndDayAvailable",
                query = "SELECT e FROM Employee e JOIN e.skills s WHERE :day IN e.daysAvailable AND s IN :skills")})


@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", nullable = false)
    private long id;

    @Nationalized
    private String name;

    @ElementCollection(targetClass = EmployeeSkill.class)
    @JoinTable(name = "employee_skills", joinColumns = @JoinColumn(name = "employee_id"))
    @Column(name = "skill", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> skills;

    @ElementCollection(targetClass = DayOfWeek.class)
    @JoinTable(name = "employee_days", joinColumns = @JoinColumn(name = "employee_id"))
    @Column(name = "days", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> daysAvailable;


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

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass() == Employee.class)
            return this.id == ((Employee) obj).id;
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
