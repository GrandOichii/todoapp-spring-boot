package grandoichii.dev.todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import grandoichii.dev.todoapp.model.Client;

@Repository
public interface ClientRepositoryImpl 
    extends JpaRepository<Client, Integer>, ClientRepository 
{
}