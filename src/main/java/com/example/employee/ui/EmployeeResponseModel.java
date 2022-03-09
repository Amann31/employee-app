package com.example.employee.ui;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeResponseModel {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
