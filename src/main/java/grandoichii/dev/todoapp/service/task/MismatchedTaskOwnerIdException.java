package grandoichii.dev.todoapp.service.task;

public class MismatchedTaskOwnerIdException extends TaskException {
    public MismatchedTaskOwnerIdException() {}
    
    public MismatchedTaskOwnerIdException(String message) {
        super(message);
    }
}
