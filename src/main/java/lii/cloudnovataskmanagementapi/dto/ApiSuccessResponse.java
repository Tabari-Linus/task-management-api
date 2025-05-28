package lii.cloudnovataskmanagementapi.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

@Data
public class ApiSuccessResponse {
    private String message;
    private Object data;
    private LocalDateTime timestamp;
    private int status;

    public ApiSuccessResponse(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.status = 200;
    }

    public ApiSuccessResponse(String message, Object data) {
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
        this.status = 200;
    }

    public ApiSuccessResponse(String message, Object data, int status) {
        this.message = message;
        this.data = data;
        this.status =  status;
        this.timestamp = LocalDateTime.now();
    }
}
