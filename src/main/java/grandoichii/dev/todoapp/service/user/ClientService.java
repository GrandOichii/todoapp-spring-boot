package grandoichii.dev.todoapp.service.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import grandoichii.dev.todoapp.dto.PostClient;
import grandoichii.dev.todoapp.model.Client;
import grandoichii.dev.todoapp.repository.ClientRepository;

@Service
public class ClientService {
    @Autowired
    private final ClientRepository clientRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

	private static final Logger log = LoggerFactory.getLogger(ClientService.class);

    public ClientService(ClientRepository clientRepository, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(PostClient client)
        throws ClientRegisterException
    {
        var existing = clientRepository.findByUsername(client.username());
        if (existing.isPresent())
            throw new ClientRegisterException(String.format("username %s it taken", client.username()));

        // TODO change to object mapping
        var password = passwordEncoder.encode(client.password());
        var newClient = new Client(client.username(), password);
        clientRepository.save(newClient);
    }

    public Client findByUsername(String username)
        throws ClientNotFoundException
    {
        var result = clientRepository.findByUsername(username);
        if (result.isEmpty()) {
            throw new ClientNotFoundException(String.format("user %s not found", username));
        }
        return result.get();
    }
}
