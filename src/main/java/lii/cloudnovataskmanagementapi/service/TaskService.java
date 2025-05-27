package lii.cloudnovataskmanagementapi.service;

import lii.cloudnovataskmanagementapi.dto.TaskDTO;
import lii.cloudnovataskmanagementapi.dto.TaskRequest;
import lii.cloudnovataskmanagementapi.model.Task;
import lii.cloudnovataskmanagementapi.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService implements TaskServiceInterface {

    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }





    private TaskDTO entityToDTO(Task task) {
        return TaskDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .dueDate(task.getDueDate())
                .build();
    }

    private void validateTaskRequest(TaskRequest taskRequest) {
        if (taskRequest.getTitle() == null || taskRequest.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Task title cannot be null or empty");
        }
        if (taskRequest.getDescription() == null || taskRequest.getDescription().trim().isEmpty()) {
            throw new IllegalArgumentException("Task description cannot be null or empty");
        }
    }
}
