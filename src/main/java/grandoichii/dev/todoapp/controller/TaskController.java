package grandoichii.dev.todoapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import grandoichii.dev.todoapp.model.Task;
import grandoichii.dev.todoapp.query.TaskQuery;
import grandoichii.dev.todoapp.service.task.TaskNotFoundException;
import grandoichii.dev.todoapp.service.task.TaskService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/all")
    List<Task> getAll() {
        return taskService.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    Task create(@Valid @RequestBody Task task) {
        return taskService.add(task);
    }

    @GetMapping("/{id}")
    Task getById(@PathVariable Integer id) {
        try {
            var result = taskService.findById(id);
            return result;            
        } catch (TaskNotFoundException e) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, e.getMessage()
            );
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/toggle/{id}")
    void toggleComplete(@PathVariable Integer id) {
        try {
            taskService.toggleComplete(id); 
        } catch (TaskNotFoundException e) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, e.getMessage()
            );
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        try {
            taskService.delete(id); 
        } catch (TaskNotFoundException e) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, e.getMessage()
            );
        }
    }

    @GetMapping()
    List<Task> query(TaskQuery query) {
        return taskService.query(query);
    }

}
