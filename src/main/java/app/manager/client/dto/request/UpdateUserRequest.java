package app.manager.client.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserRequest{
    @NotBlank(message = "Username is required")
    private String username;

    @Email
    @NotBlank(message = "Mail is required")
    private String mail;
}
