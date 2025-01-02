package app.manager.client.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReviewDTO {

    @Min(value = 0, message = "Rating must be >= 0 or < 5")
    @Max(value = 5, message = "Rating must be >= 0 or < 5")
    private int rating;

    @Size(max = 500, message = "Comment length must be <= 500 words")
    private String comment;

    @NotNull(message = "Product' Code can not be null")
    private String code;
}
