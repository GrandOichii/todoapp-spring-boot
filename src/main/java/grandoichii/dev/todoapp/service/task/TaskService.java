package grandoichii.dev.todoapp.service.task;

import java.util.List;

import grandoichii.dev.todoapp.model.Task;

public interface TaskService {
    public List<Task> findAll(Integer ownerId);

    public Task add(Task newTask, Integer ownerId);

    public Task findById(Integer id, Integer ownerId)
        throws TaskNotFoundException, MismatchedTaskOwnerIdException
    ;
    
    public void toggleComplete(Integer id, Integer ownerId)
        throws TaskNotFoundException, MismatchedTaskOwnerIdException
    ;

    public void delete(Integer id, Integer ownerId)
        throws TaskNotFoundException, MismatchedTaskOwnerIdException
    ;
}
