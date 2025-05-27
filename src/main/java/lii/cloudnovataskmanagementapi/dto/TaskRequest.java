package lii.cloudnovataskmanagementapi.dto;

import lii.cloudnovataskmanagementapi.enums.TaskEnums.TaskPriority;
import lii.cloudnovataskmanagementapi.enums.TaskEnums.TaskStatus;

import java.time.LocalDateTime;


public class TaskRequest {
    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private LocalDateTime dueDate;

    // Constructors
    public TaskRequest() {}

    public TaskRequest(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
