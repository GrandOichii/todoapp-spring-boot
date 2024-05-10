package grandoichii.dev.todoapp.service.user;

public class ClientNotFoundException extends Exception {
    public ClientNotFoundException() {}
    public ClientNotFoundException(String message) { super(message); }
}
