package lii.cloudnovataskmanagementapi.repository;

import lii.cloudnovataskmanagementapi.enums.TaskPriority;
import lii.cloudnovataskmanagementapi.model.Task;
import lii.cloudnovataskmanagementapi.enums.TaskStatus;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class TaskRepository {

    private final Map<UUID, Task> taskdb = new ConcurrentHashMap<>();


    public void creatTask(Task task) {
        if (task.getId() == null) {
            task.setId(UUID.randomUUID() );
        }
        taskdb.put(task.getId(), task);
    }

    public Task updateTask(Task task) {
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

    public List<Task> findTaskByKeyWord(String keyword) {
        return taskdb.values().stream()
                .filter(task -> (task.getTitle() != null && task.getTitle().toLowerCase().contains(keyword.toLowerCase())) ||
                        (task.getDescription() != null && task.getDescription().toLowerCase().contains(keyword.toLowerCase())) ||
                        (task.getPriority() != null && task.getPriority().toString().toLowerCase().contains(keyword.toLowerCase())) ||
                        (task.getStatus() != null && task.getStatus().toString().toLowerCase().contains(keyword.toLowerCase()))
                )
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
