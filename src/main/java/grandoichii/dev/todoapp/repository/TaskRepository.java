package grandoichii.dev.todoapp.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import grandoichii.dev.todoapp.model.Task;
import grandoichii.dev.todoapp.query.TaskQuery;

@Repository
public interface TaskRepository extends ListCrudRepository<Task, Integer> {
    @Query("SELECT * FROM Task WHERE title LIKE :#{#query.title} AND text LIKE :#{#query.text}")
    List<Task> findByQuery(@Param("query") TaskQuery query);
}
