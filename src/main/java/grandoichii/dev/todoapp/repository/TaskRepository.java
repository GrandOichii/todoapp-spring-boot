package grandoichii.dev.todoapp.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import grandoichii.dev.todoapp.model.Task;
import jakarta.annotation.PostConstruct;

@Repository
public class TaskRepository {
    private final List<Task> tasks = new ArrayList<>();

    public List<Task> findAll() {
        return tasks;
    }

    public Optional<Task> findById(String id) {
        var result = tasks
            .stream()
            .filter(t -> t.id().equals(id))
            .findFirst();
        return result;
    }

    public Task add(Task newTask) {
        tasks.add(newTask);
        return newTask;
    }

    public void update(String id, Task newTask) {
        tasks.replaceAll(t -> t.id().equals(id) ? newTask : t);
    }

    @PostConstruct
    private void init() {
        tasks.add(new Task(null, "task1", "this is the first task", false));
        tasks.add(new Task(null, "task2", "this is the second task", false));
        tasks.add(new Task(null, "task3", "this is the third task", false));
    }
}
