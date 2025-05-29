package lii.cloudnovataskmanagementapi.service;

import lii.cloudnovataskmanagementapi.dto.TaskResponse;
import lii.cloudnovataskmanagementapi.dto.TaskRequest;
import lii.cloudnovataskmanagementapi.enums.TaskStatus;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    void createTask(TaskRequest taskRequest);

    TaskResponse getTaskById(UUID id);

    List<TaskResponse> getAllTasks();

    List<TaskResponse> getTasksByStatus(TaskStatus status);

    List<TaskResponse> searchTasksByTitle(String title);

    List<TaskResponse> searchTaskByKeyWord(String keyword);

    TaskResponse updateTask(UUID id, TaskRequest taskRequest);

    TaskResponse updateTaskStatus(UUID id, TaskStatus status);

    void deleteTask(UUID id);

    long getTaskCount();

}
