package grandoichii.dev.todoapp.service.user;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import grandoichii.dev.todoapp.config.auth.JwtService;
import grandoichii.dev.todoapp.dto.LoginResult;
import grandoichii.dev.todoapp.dto.PostClient;
import grandoichii.dev.todoapp.model.Client;
import grandoichii.dev.todoapp.repository.ClientRepository;

@Service
public class ClientService implements UserDetailsService {
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public ClientService(ClientRepository clientRepository, PasswordEncoder passwordEncoder, JwtService jwtService, @Lazy AuthenticationManager authenticationManager) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
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

    public LoginResult login(PostClient client)
        throws ClientNotFoundException
    {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            client.username(),
            client.password())
        );
        var existing = findByUsername(client.username());
        var token = jwtService.generate(new HashMap<>(), existing, existing.getId());
        return new LoginResult(existing.getId(), token);
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return findByUsername(username);
        } catch (ClientNotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
    }
}
