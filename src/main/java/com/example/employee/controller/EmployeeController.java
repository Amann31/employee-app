package com.example.employee.controller;

import com.example.employee.Dto.EmployeeDto;

import com.example.employee.service.EmployeeService;
import com.example.employee.ui.EmployeeRequestModel;
import com.example.employee.ui.EmployeeResponseModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@CrossOrigin(origins = "*")
@RestController
public class EmployeeController {
    private final ModelMapper modelMapper;
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(ModelMapper modelMapper, EmployeeService employeeService) {
        this.modelMapper = modelMapper;
        this.employeeService = employeeService;
    }
    //post mapping for creating employee
    @PostMapping("/employees")
    public ResponseEntity<EmployeeResponseModel> createEmployee(@RequestBody EmployeeRequestModel employeeRequestModel) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        EmployeeDto employeeDto = modelMapper.map(employeeRequestModel, EmployeeDto.class);
        employeeDto.setId((long) new Random().nextInt(1000));
        employeeDto = employeeService.createEmployee(employeeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(employeeDto,EmployeeResponseModel.class));
    }
    //get mapping
    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeResponseModel>> getEmployees()
    {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        List<EmployeeResponseModel> list=new ArrayList<>();
        List<EmployeeDto> dtos=employeeService.getEmployees();
        for (EmployeeDto e:dtos)
        {
            list.add(modelMapper.map(e,EmployeeResponseModel.class));
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeResponseModel> findEmployeeById(@PathVariable("id") Long id)
    {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return ResponseEntity.ok(modelMapper.map(employeeService.findEmployeeById(id),EmployeeResponseModel.class));

    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<EmployeeResponseModel> updateEmployeeById(@RequestBody EmployeeRequestModel employeeRequestModel,@PathVariable("id") Long id )
    {  modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return ResponseEntity.ok(modelMapper.map(employeeService.updateEmployeeById(employeeRequestModel,id),EmployeeResponseModel.class));
    }


    @DeleteMapping("/employees/{id}")
    public ResponseEntity<?> deleteEmployeeById(@PathVariable("id") Long id) {
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
