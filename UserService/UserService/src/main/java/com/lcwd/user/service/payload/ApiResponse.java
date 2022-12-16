package com.lcwd.user.service.payload;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Builder
@Data
public class ApiResponse {
    private String message;
    private boolean success;
    private HttpStatus status;
}
