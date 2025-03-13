package ge.guka.CarCommerce.cars.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ImageUpdateRequest {
    @NotBlank(message = "არ უნდა იყოს ცარიელი")
    private String imageUrl;
}
