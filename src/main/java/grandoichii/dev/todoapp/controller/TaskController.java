package grandoichii.dev.todoapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import grandoichii.dev.todoapp.config.auth.JwtService;
import grandoichii.dev.todoapp.model.Task;
import grandoichii.dev.todoapp.service.task.TaskException;
import grandoichii.dev.todoapp.service.task.TaskService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    private static final Logger log = LoggerFactory.getLogger(TaskController.class);

    private final TaskService taskService;
    private final JwtService jwtService;
    
    @Autowired
    public TaskController(TaskService taskService, JwtService jwtService) {
        this.taskService = taskService;
        this.jwtService = jwtService;
    }

    @GetMapping("/all")
    @SecurityRequirement(name = "Authorization")
    List<Task> getAll(
        @RequestHeader (name="Authorization") String token
    ) {
        var id = getId(token);
        return taskService.findAll(id);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @SecurityRequirement(name = "Authorization")
    Task create(
        @Valid @RequestBody Task task,
        @RequestHeader (name="Authorization") String token
    ) {
        var id = getId(token);
        return taskService.add(task, id);
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "Authorization")
    Task getById(
        @PathVariable Integer id,
        @RequestHeader (name="Authorization") String token
    ) {
        try {
            var ownerId = getId(token);
            var result = taskService.findById(id, ownerId);
            return result;            
        } catch (TaskException e) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, e.getMessage()
            );
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/toggle/{id}")
    @SecurityRequirement(name = "Authorization")
    void toggleComplete(
        @PathVariable Integer id,
        @RequestHeader (name="Authorization") String token
    ) {
        try {
            var ownerId = getId(token);

            taskService.toggleComplete(id, ownerId); 
        } catch (TaskException e) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, e.getMessage()
            );
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Authorization")
    void delete(
        @PathVariable Integer id,
        @RequestHeader (name="Authorization") String token
    ) {
        try {
            var ownerId = getId(token);

            taskService.delete(id, ownerId); 
        } catch (TaskException e) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, e.getMessage()
            );
        }
    }

    private Integer getId(String token) {
        return jwtService.extractId(token.substring(7));
    }

    // @GetMapping()
    // List<Task> query(TaskQuery query) {
    //     return taskService.query(query);
    // }

}
