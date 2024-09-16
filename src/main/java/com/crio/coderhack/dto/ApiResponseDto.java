package com.crio.coderhack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseDto<T> {

    // Status of the response (e.g., success, error)
    private String status;

    // Message providing details about the response
    private String message;

    // Data payload, if any, associated with the response
    private T data;
}
