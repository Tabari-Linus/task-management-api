package lii.cloudnovataskmanagementapi.model;

import lii.cloudnovataskmanagementapi.enums.TaskPriority;
import lii.cloudnovataskmanagementapi.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class Task {

    private UUID id;
    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime dueDate;

    public Task() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.status = TaskStatus.PENDING;
        this.priority = TaskPriority.MEDIUM;
    }

    public Task(String title, String description) {
        this();
        this.title = title;
        this.description = description;
    }

}



