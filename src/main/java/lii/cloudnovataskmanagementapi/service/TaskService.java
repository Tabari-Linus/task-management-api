package lii.cloudnovataskmanagementapi.service;

import lii.cloudnovataskmanagementapi.dto.TaskResponse;
import lii.cloudnovataskmanagementapi.dto.TaskRequest;
import lii.cloudnovataskmanagementapi.enums.TaskStatus;
import lii.cloudnovataskmanagementapi.exception.TaskNotFoundException;
import lii.cloudnovataskmanagementapi.model.Task;
import lii.cloudnovataskmanagementapi.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class TaskService implements TaskServiceInterface {

    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskResponse createTask(TaskRequest taskRequest) {
        validateTaskRequest(taskRequest);

        Task task = new Task(taskRequest.getTitle(), taskRequest.getDescription());

        if (taskRequest.getStatus() != null) {
            task.setStatus(taskRequest.getStatus());
        }
        if (taskRequest.getPriority() != null) {
            task.setPriority(taskRequest.getPriority());
        }
        if (taskRequest.getDueDate() != null) {
            task.setDueDate(taskRequest.getDueDate());
        }

        Task savedTask = taskRepository.creatTask(task);
        return entityToDTO(savedTask);
    }

    @Override
    public TaskResponse getTaskById(UUID id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException( id));
        return entityToDTO(task);
    }

    @Override
    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskResponse> getTasksByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status).stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskResponse> searchTasksByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            return getAllTasks();
        }
        return taskRepository.findByTitleContaining(title.trim()).stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }


    @Override
    public TaskResponse updateTask(UUID id, TaskRequest taskRequest) {
        validateTaskRequest(taskRequest);

        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        existingTask.setTitle(taskRequest.getTitle());
        existingTask.setDescription(taskRequest.getDescription());

        if (taskRequest.getStatus() != null) {
            existingTask.setStatus(taskRequest.getStatus());
        }
        if (taskRequest.getPriority() != null) {
            existingTask.setPriority(taskRequest.getPriority());
        }
        if (taskRequest.getDueDate() != null) {
            existingTask.setDueDate(taskRequest.getDueDate());
        }

        Task updatedTask = taskRepository.creatTask(existingTask);
        return entityToDTO(updatedTask);
    }


    @Override
    public TaskResponse updateTaskStatus(UUID id, TaskStatus status) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        existingTask.setStatus(status);
        Task updatedTask = taskRepository.creatTask(existingTask);
        return entityToDTO(updatedTask);
    }

    @Override
    public void deleteTask(UUID id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
        taskRepository.deleteById(id);
    }

    @Override
    public long getTaskCount() {
        return taskRepository.count();
    }

    public TaskResponse entityToDTO(Task task) {
        return TaskResponse.builder()
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
