package lii.cloudnovataskmanagementapi.dto;

import lii.cloudnovataskmanagementapi.enums.TaskPriority;
import lii.cloudnovataskmanagementapi.enums.TaskStatus;
import lombok.Data;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Data
public class TaskRequest {

    @NotBlank(message = "Title is required and cannot be blank")
    private String title;
    @NotBlank(message = "Description is required and cannot be blank")
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    @Future(message = "Due date must be in the future")
    private LocalDateTime dueDate;

    public TaskRequest() {}

    public TaskRequest(String title, String description) {
        this.title = title;
        this.description = description;
    }
}