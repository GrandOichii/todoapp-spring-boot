package grandoichii.dev.todoapp.query;

public record TaskQuery(
    String title,
    String text
) {
    public TaskQuery {
        if (title == null) title = "";
        if (text == null) text = "";

        // TODO there has to be a better way
        title = "%" + title + "%";
        text = "%" + text + "%";
    }
}
