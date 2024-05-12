package grandoichii.dev.todoapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import grandoichii.dev.todoapp.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    // @Query("SELECT * FROM Task WHERE title LIKE :#{#query.title} AND text LIKE :#{#query.text}")
    // List<Task> findByQuery(@Param("query") TaskQuery query);

    List<Task> findByOwnerId(Integer ownerId);
}
