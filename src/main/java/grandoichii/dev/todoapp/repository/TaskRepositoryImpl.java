package grandoichii.dev.todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import grandoichii.dev.todoapp.model.Task;

@Repository
public interface TaskRepositoryImpl extends JpaRepository<Task, Integer>, TaskRepository {
}
