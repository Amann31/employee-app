package com.example.employee.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "employee_table")
public class Employee {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

}
