package ge.guka.CarCommerce.cars.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewRequest {
    @Positive
    private Long carId;
    @Positive
    private Long userId;
    @Min(0)
    @Max(5)
    private Integer rating;
    @NotBlank(message = "შეფასების ველი არ უნდა იყოს ცარიელი")
    private String review;
}
