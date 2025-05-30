package lii.cloudnovataskmanagementapi.controller;

import lii.cloudnovataskmanagementapi.dto.ApiSuccessResponse;
import lii.cloudnovataskmanagementapi.dto.TaskResponse;
import lii.cloudnovataskmanagementapi.dto.TaskRequest;
import lii.cloudnovataskmanagementapi.enums.TaskPriority;
import lii.cloudnovataskmanagementapi.enums.TaskStatus;
import lii.cloudnovataskmanagementapi.service.TaskQueryService;
import lii.cloudnovataskmanagementapi.service.TaskServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskServiceImpl taskServiceImpl;
    private final TaskQueryService taskQueryService;

    public TaskController(TaskServiceImpl taskServiceImpl, TaskQueryService taskQueryService) {
        this.taskServiceImpl = taskServiceImpl;
        this.taskQueryService = taskQueryService;
    }

    @PostMapping
    public ResponseEntity<ApiSuccessResponse> createTask(@Valid @RequestBody TaskRequest task) {
        taskServiceImpl.createTask(task);
        return new ResponseEntity<>(
                new ApiSuccessResponse("Task created successfully", "Created", HttpStatus.CREATED.value()),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks(
            @RequestParam(required = false) TaskStatus status,
            @RequestParam(required = false) String title) {
        List<TaskResponse> tasks = taskQueryService.getTasks(status, title);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable UUID id) {
        TaskResponse task = taskServiceImpl.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable UUID id,@Valid @RequestBody TaskRequest taskRequest) {
        TaskResponse updatedTask = taskServiceImpl.updateTask(id, taskRequest);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteTask(@PathVariable UUID id) {
        taskServiceImpl.deleteTask(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Task deleted successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<TaskResponse> updateTaskStatus(@PathVariable UUID id, @RequestParam  TaskStatus status) {
        TaskResponse updatedTask = taskServiceImpl.updateTaskStatus(id, status);
        return ResponseEntity.ok(updatedTask);
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getTaskStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalTasks", taskServiceImpl.getTaskCount());
        stats.put("pendingTasks", taskServiceImpl.getTasksByStatus(TaskStatus.PENDING).size());
        stats.put("inProgressTasks", taskServiceImpl.getTasksByStatus(TaskStatus.IN_PROGRESS).size());
        stats.put("completedTasks", taskServiceImpl.getTasksByStatus(TaskStatus.COMPLETED).size());
        stats.put("cancelledTasks", taskServiceImpl.getTasksByStatus(TaskStatus.CANCELLED).size());
        stats.put("lowPriorityTask", taskServiceImpl.getTaskByPriority(TaskPriority.LOW).size());
        stats.put("highPriorityTask", taskServiceImpl.getTaskByPriority(TaskPriority.HIGH).size());
        stats.put("mediumPriorityTask", taskServiceImpl.getTaskByPriority(TaskPriority.MEDIUM).size());
        stats.put("urgencyTask", taskServiceImpl.getTaskByPriority(TaskPriority.URGENT).size());
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/search")
    public ResponseEntity<List<TaskResponse>> searchTasks(@RequestParam(required = false) String keyword) {
        return ResponseEntity.ok(taskServiceImpl.searchTaskByKeyWord(keyword));
    }
}