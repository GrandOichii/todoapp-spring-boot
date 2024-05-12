package grandoichii.dev.todoapp.service.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grandoichii.dev.todoapp.model.Task;
import grandoichii.dev.todoapp.repository.TaskRepository;


@Service
public class TaskService {
    private final TaskRepository taskRepository;
    
    @Autowired
    public TaskService(TaskRepository taskRsepository) {
        this.taskRepository = taskRsepository;
    }    

    public List<Task> findAll(Integer ownerId) {
        return taskRepository.findByOwnerId(ownerId);
    }

    public Task add(Task newTask, Integer ownerId) {
        newTask.setOwnerId(ownerId);
        return taskRepository.save(newTask);
    }

    public Task findById(Integer id, Integer ownerId)
        throws TaskNotFoundException, MismatchedTaskOwnerIdException
    {
        var result = taskRepository.findById(id);
        if (result.isEmpty()) 
            throw new TaskNotFoundException(String.format("task with id %s not found", id));
        var task =  result.get();
        if (!task.getOwnerId().equals(ownerId))
            throw new MismatchedTaskOwnerIdException("task %s is not owned by %s".formatted(id, ownerId));
        return task;
    }

    public void toggleComplete(Integer id, Integer ownerId)
        throws TaskNotFoundException, MismatchedTaskOwnerIdException
    {
        var result = taskRepository.findById(id);
        if (result.isEmpty()) 
            throw new TaskNotFoundException(String.format("task with id %s not found", id));
            
        var task = result.get();
        if (!task.getOwnerId().equals(ownerId))
            throw new MismatchedTaskOwnerIdException("task %s is not owned by %s".formatted(id, ownerId));

        task.setCompleted(!task.getCompleted());
        taskRepository.save(task);
    }

    public void delete(Integer id, Integer ownerId)
        throws TaskNotFoundException, MismatchedTaskOwnerIdException
    {
        findById(id, ownerId);
        taskRepository.deleteById(id);
    }

    // public List<Task> query(TaskQuery query) {
    //     return taskRepository.findByQuery(query);
    // }
}
