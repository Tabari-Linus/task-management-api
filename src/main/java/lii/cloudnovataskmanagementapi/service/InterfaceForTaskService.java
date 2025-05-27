package lii.cloudnovataskmanagementapi.service;

import lii.cloudnovataskmanagementapi.dto.TaskDTO;
import lii.cloudnovataskmanagementapi.dto.TaskRequest;
import lii.cloudnovataskmanagementapi.enums.TaskEnums.TaskStatus;

import java.util.List;
import java.util.UUID;

public interface InterfaceForTaskService {

    TaskDTO createTask(TaskRequest taskRequest);

    TaskDTO getTaskById(UUID id);

    List<TaskDTO> getAllTasks();

    List<TaskDTO> getTasksByStatus(TaskStatus status);

    List<TaskDTO> searchTasksByTitle(String title);

    TaskDTO updateTask(UUID id, TaskRequest taskRequest);

    TaskDTO updateTaskStatus(UUID id, TaskStatus status);

    void deleteTask(UUID id);

    long getTaskCount();

}
