package ge.guka.CarCommerce.cars.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequest {
    @NotBlank(message = "არ უნდა იყოს ცარიელი")
    private String username;

    @NotBlank(message = "არ უდნა იყოს ცარიელი")
    private String password;
}
