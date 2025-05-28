package lii.cloudnovataskmanagementapi.controller;

import lii.cloudnovataskmanagementapi.dto.TaskDTO;
import lii.cloudnovataskmanagementapi.dto.TaskRequest;
import lii.cloudnovataskmanagementapi.enums.TaskStatus;
import lii.cloudnovataskmanagementapi.model.Task;
import lii.cloudnovataskmanagementapi.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public Task createTask(@RequestBody TaskRequest task) {
        TaskDTO taskDTO = taskService.createTask(task);
        return dtoToEntity(taskDTO);
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks(
            @RequestParam(required = false) TaskStatus status,
            @RequestParam(required = false) String search) {

        List<TaskDTO> tasks;

        if (status != null) {
            tasks = taskService.getTasksByStatus(status);
        } else if (search != null) {
            tasks = taskService.searchTasksByTitle(search);
        } else {
            tasks = taskService.getAllTasks();
        }

        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable UUID id) {
        TaskDTO task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }



    public Task dtoToEntity(TaskDTO dto) {
        return Task.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .dueDate(dto.getDueDate())
                .status(dto.getStatus())
                .build();
    }
}
