package grandoichii.dev.todoapp.repository;

import java.util.List;
import java.util.Optional;

import grandoichii.dev.todoapp.model.Task;

public interface TaskRepository {
    List<Task> findByOwnerId(Integer ownerId);

    // TODO add back
    // @Query("SELECT * FROM Task WHERE title LIKE :#{#query.title} AND text LIKE :#{#query.text}")
    // List<Task> findByQuery(@Param("query") TaskQuery query);

    Task save(Task task);
    Optional<Task> findById(Integer id);
    void deleteById(Integer id);
    
}
