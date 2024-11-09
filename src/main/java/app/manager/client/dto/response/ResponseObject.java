package app.manager.client.dto.response;

import app.manager.client.dto.Response;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseObject<T> {
    private String status;
    private String error;
    private T data;

    public ResponseObject(String status, T data){
        this.status = status;
        this.data = data;
    }

    public ResponseObject(String status, String error, T data){
        this.status = status;
        this.error = error;
        this.data = data;
    }

    public ResponseObject(String status, String error) {
        this.status = status;
        this.error = error;
    }
}
