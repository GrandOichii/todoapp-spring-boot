package grandoichii.dev.todoapp.service.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grandoichii.dev.todoapp.model.Task;
import grandoichii.dev.todoapp.repository.TaskRepository;


@Service
public class TaskService {
    @Autowired
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRsepository) {
        this.taskRepository = taskRsepository;
    }    

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task add(Task newTask) {
        return taskRepository.save(newTask);
    }

    public Task findById(Integer id)
        throws TaskNotFoundException
    {
        var result = taskRepository.findById(id);
        if (result.isPresent()) return result.get();

        throw new TaskNotFoundException(String.format("task with id %s not found", id));
    }

    public void toggleComplete(Integer id)
        throws TaskNotFoundException
    {
        var result = taskRepository.findById(id);
        if (result.isEmpty()) 
            throw new TaskNotFoundException(String.format("task with id %s not found", id));

        var task = result.get();
        task.setCompleted(!task.getCompleted());
        taskRepository.save(task);
    }

    public void delete(Integer id)
        throws TaskNotFoundException
    {
        taskRepository.deleteById(id);
    }

    // public List<Task> query(TaskQuery query) {
    //     return taskRepository.findByQuery(query);
    // }
}
