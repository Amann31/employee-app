package com.example.employee;

import com.example.employee.exception.ErrorResponseModel;
import com.example.employee.model.Employee;
import com.example.employee.repo.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EmployeeApplication implements CommandLineRunner {
    private final EmployeeRepository employeeRepository;

    public EmployeeApplication(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Bean
    public ErrorResponseModel errorResponseModel() {
        return new ErrorResponseModel();
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    public static void main(String[] args) {
        SpringApplication.run(EmployeeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        employeeRepository.save(new Employee(1l,"Aman","Sharma","aman@email.com"));
        employeeRepository.save(new Employee(2l,"Kshitij","Sharma","kshitij@email.com"));
    }
}
