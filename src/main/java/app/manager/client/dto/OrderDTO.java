package app.manager.client.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class OrderDTO {
    @NotBlank(message = "CODE is required")
    private String code;

    @Min(value = 1, message = "Quantity must be greater than 0")
    private double quantity;
}
