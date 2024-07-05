package app.manager.client.dto.response;

import app.manager.client.dto.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseObject extends Response {
    private String status;
    private Object data;

    public ResponseObject(String message, String status, Object data){
        super(message);
        this.status = status;
        this.data = data;
    }
}
