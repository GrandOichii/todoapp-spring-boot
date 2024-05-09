package grandoichii.dev.todoapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import grandoichii.dev.todoapp.model.Task;
import grandoichii.dev.todoapp.repository.TaskRepository;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRsepository) {
        this.taskRepository = taskRsepository;
    }

    @GetMapping("/all")
    List<Task> getAll() {
        return taskRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    Task create(@RequestBody Task task) {
        return taskRepository.add(task);
    }

    @GetMapping("/{id}")
    Optional<Task> getById(@PathVariable String id) {
        var result = taskRepository.findById(id);
        return result;
    }

}
