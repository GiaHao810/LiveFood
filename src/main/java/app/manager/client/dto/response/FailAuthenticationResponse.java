package app.manager.client.dto.response;

import app.manager.client.dto.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class FailAuthenticationResponse extends Response {
    private String status;
    private Object data;

    public FailAuthenticationResponse(String message, String status, Object data) {
        super(message);
        this.status = status;
        this.data = data;
    }
}
