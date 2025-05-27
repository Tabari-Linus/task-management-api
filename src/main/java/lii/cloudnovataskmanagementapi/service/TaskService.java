package lii.cloudnovataskmanagementapi.service;

import lii.cloudnovataskmanagementapi.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }



}
