package com.example.employee.service;

import com.example.employee.Dto.EmployeeDto;
import com.example.employee.exception.EmployeeNotFoundException;
import com.example.employee.model.Employee;
import com.example.employee.repo.EmployeeRepository;
import com.example.employee.ui.EmployeeRequestModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private final ModelMapper modelMapper;
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(ModelMapper modelMapper, EmployeeRepository employeeRepository) {
        this.modelMapper = modelMapper;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Employee employee = modelMapper.map(employeeDto,Employee.class);
        employee = employeeRepository.save(employee);
        return modelMapper.map(employee,EmployeeDto.class);
    }

    @Override
    public List<EmployeeDto> getEmployees() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        List<EmployeeDto> list=new ArrayList<>();
        Iterable<Employee> iterable= employeeRepository.findAll();

        Iterator<Employee> iterator= iterable.iterator();
        while (iterator.hasNext())
        {
            list.add(modelMapper.map(iterator.next(),EmployeeDto.class));
        }
        return list;
    }

    private Employee findEmployeeId(Long id) {
        Employee employee = employeeRepository.findByid(id);
        return employee;
    }

    @Override
    public EmployeeDto findEmployeeById(Long id) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Employee employee=findEmployeeId(id);
        return modelMapper.map(employee,EmployeeDto.class);
    }

    @Override
    public void deleteEmployeeById(Long id) {
        Employee employee = findEmployeeId(id);
        if(employee==null)
        {
            throw new EmployeeNotFoundException("employee with id: "+id+" not found");
        }

        employeeRepository.delete(employee);

    }

    @Override
    public EmployeeDto updateEmployeeById(EmployeeRequestModel employeeRequestModel, Long id) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Employee employee= findEmployeeId(id);
        if(employee==null)
        {
            throw new EmployeeNotFoundException("Employee with id: "+id+" not found");
        }
        employee.setFirstName(employeeRequestModel.getFirstName());
        employee.setLastName(employeeRequestModel.getLastName());
        employee.setEmail(employeeRequestModel.getEmail());
        employee= employeeRepository.save(employee);
        return modelMapper.map(employee,EmployeeDto.class);
    }
}
