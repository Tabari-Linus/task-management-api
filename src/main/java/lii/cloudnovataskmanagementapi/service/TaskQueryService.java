package lii.cloudnovataskmanagementapi.service;

import lii.cloudnovataskmanagementapi.dto.TaskResponse;
import lii.cloudnovataskmanagementapi.enums.TaskStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskQueryService {

    private final TaskServiceImpl taskServiceImpl;

    public TaskQueryService(TaskServiceImpl taskServiceImpl) {
        this.taskServiceImpl = taskServiceImpl;
    }

    public List<TaskResponse> getTasks(TaskStatus status, String title) {
        if (status != null) {
            return taskServiceImpl.getTasksByStatus(status);
        }

        if (title != null && !title.trim().isEmpty()) {
            return taskServiceImpl.searchTasksByTitle(title.trim());
        }

        return taskServiceImpl.getAllTasks();
    }
}