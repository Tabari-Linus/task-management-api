package lii.cloudnovataskmanagementapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiErrorResponse {
    private String message;
    private String status;
    private int statusCode;
}
