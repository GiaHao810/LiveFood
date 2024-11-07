package app.manager.client.dto.request;

import app.manager.client.model.Category;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddProductRequest {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Price is required")
    private Double price;

    @NotBlank(message = "Category is required")
    private String category;
}
