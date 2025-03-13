package ge.guka.CarCommerce.cars.model;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EngineRequest {
    @Positive(message = "ცხენის ძალა უნდა იყოს მხოლოდ დადებითი ციფრი")
    private int horsePower;
    @Positive(message = "მხოლოდ დადებითი ციფრი")
    private double capacity;

}
