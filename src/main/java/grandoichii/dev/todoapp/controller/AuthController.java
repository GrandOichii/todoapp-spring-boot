package grandoichii.dev.todoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import grandoichii.dev.todoapp.dto.LoginResult;
import grandoichii.dev.todoapp.dto.PostClient;
import grandoichii.dev.todoapp.service.user.ClientNotFoundException;
import grandoichii.dev.todoapp.service.user.ClientRegisterException;
import grandoichii.dev.todoapp.service.user.ClientService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final ClientService userService;
    
    @Autowired
    public AuthController(ClientService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    void register(@Valid @RequestBody PostClient client) {
        try {
            userService.register(client);
        } catch (ClientRegisterException e) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, e.getMessage()
            );
        }
    }

    @PostMapping("/login")
    LoginResult login(@Valid @RequestBody PostClient client) {
        try {
            var result = userService.login(client);
            return result;
        } catch (ClientNotFoundException e) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, e.getMessage()
            );
        }
    }
    
}
