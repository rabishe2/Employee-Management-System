package com.example.employeemanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Emp_TB")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long employeeid;
    @Column(name = "FNAME")
    @NotNull(message = "{FIRST_NULL}")
    @Pattern(regexp="^[a-zA-Z]+$", message="{FIRST_INVALID}")
    private String firstname;
    @Column(name = "LNAME")
    @NotNull(message = "{LAST_NULL}")
    @Pattern(regexp="^[a-zA-Z]+$", message="{LAST_INVALID}")
    private String lastname;
    @NotNull(message = "{DEPT_NULL}")
    @Column(name = "DEPT")
    private String department;
    @Column(name = "DESG")
    @NotNull(message = "{DESG_NULL}")
    private String designation;
    @Column(name = "SALARY")
    @NotNull(message = "{SAL_NULL}")
    @Min(value = 20000, message = "{SAL_INVALID}")
    private double salary;
}
