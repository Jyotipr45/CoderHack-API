package com.crio.coderhack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorBodyDto {
    
    // Error code for identifying the type of error
    private String errorCode;

    // Detailed message explaining the error
    private String errorMessage;
}
