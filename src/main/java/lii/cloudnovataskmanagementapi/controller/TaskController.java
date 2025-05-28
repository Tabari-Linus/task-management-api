package lii.cloudnovataskmanagementapi.controller;

import lii.cloudnovataskmanagementapi.dto.TaskDTO;
import lii.cloudnovataskmanagementapi.dto.TaskRequest;
import lii.cloudnovataskmanagementapi.model.Task;
import lii.cloudnovataskmanagementapi.service.TaskService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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




    public Task dtoToEntity(TaskDTO dto) {
        return Task.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .dueDate(dto.getDueDate())
                .status(dto.getStatus())
                .build();
    }
}
