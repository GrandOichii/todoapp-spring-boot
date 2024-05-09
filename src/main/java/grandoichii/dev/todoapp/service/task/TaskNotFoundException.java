package grandoichii.dev.todoapp.service.task;

public class TaskNotFoundException extends Exception {
    public TaskNotFoundException() {}
    
    public TaskNotFoundException(String message) {
        super(message);
    }
}