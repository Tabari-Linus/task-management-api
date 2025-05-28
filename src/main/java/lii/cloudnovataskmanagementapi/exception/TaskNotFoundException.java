package lii.cloudnovataskmanagementapi.exception;

import lombok.Getter;
import java.util.UUID;

@Getter
public class TaskNotFoundException extends RuntimeException {

    private final UUID taskId;

    public TaskNotFoundException(UUID taskId) {
        super("Task with ID " + taskId + " not found.");
        this.taskId = taskId;
    }

}
