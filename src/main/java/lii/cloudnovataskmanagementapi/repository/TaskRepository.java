package lii.cloudnovataskmanagementapi.repository;

import lii.cloudnovataskmanagementapi.model.Task;
import lii.cloudnovataskmanagementapi.enums.TaskStatus;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class TaskRepository {

    private final Map<UUID, Task> taskdb = new ConcurrentHashMap<>();


    public Task creatTask(Task task) {
        if (task.getId() == null) {
            task.setId(UUID.randomUUID() );
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

    public void deleteById(UUID id) {
        taskdb.remove(id);
    }

    public boolean existsById(UUID id) {
        return taskdb.containsKey(id);
    }

    public long count() {
        return taskdb.size();
    }


}
