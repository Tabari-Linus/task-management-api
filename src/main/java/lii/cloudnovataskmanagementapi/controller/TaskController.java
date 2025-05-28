package lii.cloudnovataskmanagementapi.controller;

import lii.cloudnovataskmanagementapi.dto.TaskDTO;
import lii.cloudnovataskmanagementapi.dto.TaskRequest;
import lii.cloudnovataskmanagementapi.enums.TaskStatus;
import lii.cloudnovataskmanagementapi.model.Task;
import lii.cloudnovataskmanagementapi.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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


    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask( @PathVariable UUID id,
            @RequestBody TaskRequest taskRequest) {
        TaskDTO updatedTask = taskService.updateTask(id, taskRequest);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteTask(@PathVariable UUID id) {
        taskService.deleteTask(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Task deleted successfully");
        response.put("id", id.toString());
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<TaskDTO> updateTaskStatus( @PathVariable UUID id, @RequestParam TaskStatus status) {
        TaskDTO updatedTask = taskService.updateTaskStatus(id, status);
        return ResponseEntity.ok(updatedTask);
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getTaskStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalTasks", taskService.getTaskCount());
        stats.put("pendingTasks", taskService.getTasksByStatus(TaskStatus.PENDING).size());
        stats.put("inProgressTasks", taskService.getTasksByStatus(TaskStatus.IN_PROGRESS).size());
        stats.put("completedTasks", taskService.getTasksByStatus(TaskStatus.COMPLETED).size());
        stats.put("cancelledTasks", taskService.getTasksByStatus(TaskStatus.CANCELLED).size());
        return ResponseEntity.ok(stats);
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
