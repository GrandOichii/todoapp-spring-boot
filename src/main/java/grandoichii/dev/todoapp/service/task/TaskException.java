package grandoichii.dev.todoapp.service.task;

public class TaskException extends Exception {
    public TaskException() {}
    
    public TaskException(String message) {
        super(message);
    }
}