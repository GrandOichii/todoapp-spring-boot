package grandoichii.dev.todoapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import jakarta.validation.constraints.NotEmpty;

public record Task (
    @Id
    Integer id,

    @NotEmpty
    String title,

    @NotEmpty
    String text,

    Boolean completed,

    @Version
    Integer version
) {
    static int count = 0;
    public Task {
        if (completed == null) completed = false;
        if (id == null) id = ++count;
    }

    public Task withCompleted(Boolean completed) {
        return new Task(this.id, this.title, this.text, completed, this.version);
    }
}
