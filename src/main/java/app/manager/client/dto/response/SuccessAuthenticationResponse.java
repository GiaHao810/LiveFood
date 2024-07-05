package app.manager.client.dto.response;

import app.manager.client.dto.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuccessAuthenticationResponse extends Response {
    private String token;
    private long expiresAt;

    public SuccessAuthenticationResponse(String message, String token, long expiresAt) {
        super(message);
        this.token = token;
        this.expiresAt = expiresAt;
    }
}
