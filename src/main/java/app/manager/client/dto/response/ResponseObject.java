package app.manager.client.dto.response;

import app.manager.client.dto.Response;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseObject<T> {
    private String status;
    private String error;
    private T data;

    public ResponseObject(String status, T data){
        this.status = status;
        this.data = data;
        this.error = null;
    }

    public ResponseObject(String status, String error, T data){
        this.status = status;
        this.error = error;
        this.data = data;
    }

    public ResponseObject(String status, String error) {
        this.status = status;
        this.error = error;
        this.data = null;
    }

    public ResponseObject(String status){
        this.status = status;
        this.error = null;
        this.data = null;
    }
}
