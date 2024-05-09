package grandoichii.dev.todoapp.model;

public record Task (
    String id,
    String title,
    String text,
    Boolean completed
) {
    static int count = 0;
    public Task {
        if (completed == null) completed = false;
        id = "" + ++count;
    }
}
