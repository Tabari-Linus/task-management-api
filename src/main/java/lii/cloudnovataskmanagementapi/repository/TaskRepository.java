package lii.cloudnovataskmanagementapi.repository;

import lii.cloudnovataskmanagementapi.model.Task;
import lii.cloudnovataskmanagementapi.model.Task.TaskStatus;
import lii.cloudnovataskmanagementapi.model.Task.TaskPriority;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class TaskRepository {

    private final Map<UUID, Task> taskdb = new ConcurrentHashMap<>();
    private final UUID generatorUUID = UUID.randomUUID() ;

    public TaskRepository() {

        initializeSampleData();
    }

    public Task creatTask(Task task) {
        if (task.getId() == null) {
            task.setId(UUID.randomUUID());
        }
        taskdb.put(task.getId(), task);
        return task;
    }

    public Optional<Task> findById(UUID id) {
        return Optional.ofNullable(taskdb.get(id));
    }

    public List<Task> findAll() {
        return new ArrayList<>(taskdb.values());
    }

    public List<Task> findByStatus(TaskStatus status) {
        return taskdb.values().stream()
                .filter(task -> task.getStatus() == status)
                .collect(Collectors.toList());
    }

    public List<Task> findByTitleContaining(String title) {
        return taskdb.values().stream()
                .filter(task -> task.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }


    private void initializeSampleData() {
        Task task1 = new Task("Setup Development Environment", "Install Java, Maven, and IDE");
        task1.setStatus(TaskStatus.COMPLETED);
        task1.setPriority(TaskPriority.HIGH);
        creatTask(task1);

        Task task2 = new Task("Implement REST API", "Create CRUD endpoints for task management");
        task2.setStatus(TaskStatus.IN_PROGRESS);
        task2.setPriority(TaskPriority.HIGH);
        creatTask(task2);

        Task task3 = new Task("Write Documentation", "Create comprehensive API documentation");
        task3.setStatus(TaskStatus.PENDING);
        task3.setPriority(TaskPriority.MEDIUM);
        creatTask(task3);
    }

}
