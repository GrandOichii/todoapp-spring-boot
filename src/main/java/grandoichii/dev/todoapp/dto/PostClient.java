package grandoichii.dev.todoapp.dto;

import jakarta.validation.constraints.NotEmpty;

public record PostClient(
    @NotEmpty
    String username,
    
    @NotEmpty
    String password
) {
    
}
