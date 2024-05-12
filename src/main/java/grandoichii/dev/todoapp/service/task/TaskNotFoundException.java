package grandoichii.dev.todoapp.service.task;

public class TaskNotFoundException extends TaskException {
    public TaskNotFoundException() {}
    
    public TaskNotFoundException(String message) {
        super(message);
    }
}