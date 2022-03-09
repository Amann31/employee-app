package com.example.employee.service;

import com.example.employee.Dto.EmployeeDto;
import com.example.employee.ui.EmployeeRequestModel;

import java.util.List;

public interface EmployeeService {
    public EmployeeDto createEmployee(EmployeeDto employeeDto);
    public List<EmployeeDto> getEmployees();
    public EmployeeDto findEmployeeById(Long id);
    public void deleteEmployeeById(Long id);
    EmployeeDto updateEmployeeById(EmployeeRequestModel employeeRequestModel, Long id);
}
