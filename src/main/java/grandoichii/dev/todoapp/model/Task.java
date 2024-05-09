package grandoichii.dev.todoapp.model;

import jakarta.validation.constraints.NotEmpty;

public record Task (
    String id,

    @NotEmpty
    String title,

    @NotEmpty
    String text,

    Boolean completed
) {
    static int count = 0;
    public Task {
        if (completed == null) completed = false;
        if (id == null) id = "" + ++count;
    }

    public Task withCompleted(Boolean completed) {
        return new Task(this.id, this.title, this.text, completed);
    }
}
