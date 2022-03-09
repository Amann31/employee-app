package com.example.employee.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorResponseModel {

    private String errorMessage;
    private Integer errorCode;
    private Long errorTime;
}
