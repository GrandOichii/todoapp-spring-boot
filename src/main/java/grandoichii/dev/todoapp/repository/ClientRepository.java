package grandoichii.dev.todoapp.repository;

import java.util.Optional;

import grandoichii.dev.todoapp.model.Client;


public interface ClientRepository {
    Optional<Client> findByUsername(String username);
    Client save(Client client);

    
}
