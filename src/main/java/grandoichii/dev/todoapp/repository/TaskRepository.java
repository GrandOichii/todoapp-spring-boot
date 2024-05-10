package grandoichii.dev.todoapp.repository;

// import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import grandoichii.dev.todoapp.model.Task;
// import jakarta.annotation.PostConstruct;

@Repository
public class TaskRepository {
	private static final Logger log = LoggerFactory.getLogger(TaskRepository.class);
    private final JdbcClient jdbcClient;

    public TaskRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Task> findAll() {
        return jdbcClient.sql("select * from task")
            .query(Task.class)
            .list();
    }

    public Optional<Task> findById(String id) {
        return jdbcClient.sql("select * from task where id = :id")
            .param("id", id)
            .query(Task.class)
            .optional();
    }

    public Task add(Task newTask) {
        // TODO replace auto genevrated id

        var updated = jdbcClient.sql("insert into Task(id, title, text, completed) values (?,?,?,?)")
            .params(List.of(newTask.id(), newTask.title(), newTask.text(), newTask.completed()))
            .update();
        Assert.state(updated == 1, "Failed to create task " + newTask.title() );
        return newTask;
    }

    public void update(String id, Task newTask) {
        var updated = jdbcClient.sql("update Task set title = ?, text = ?, completed = ? where id = ?")
            .params(List.of(newTask.title(), newTask.text(), newTask.completed(), id))
            .update();
        Assert.state(updated == 1, "Failed to update task with id " + id);
    }

    public int delete(String id) {
        return jdbcClient.sql("delete from Task where id = :id")
            .param("id", id)
            .update();
    }

    // private final List<Task> tasks = new ArrayList<>();

    // public List<Task> findAll() {
    //     return tasks;
    // }

    // public Optional<Task> findById(String id) {
    //     var result = tasks
    //         .stream()
    //         .filter(t -> t.id().equals(id))
    //         .findFirst();
    //     return result;
    // }

    // public Task add(Task newTask) {
    //     tasks.add(newTask);
    //     return newTask;
    // }

    // public void update(String id, Task newTask) {
    //     tasks.replaceAll(t -> t.id().equals(id) ? newTask : t);
    // }

    // @PostConstruct
    // private void init() {
    //     tasks.add(new Task(null, "task1", "this is the first task", false));
    //     tasks.add(new Task(null, "task2", "this is the second task", false));
    //     tasks.add(new Task(null, "task3", "this is the third task", false));
    // }

}
