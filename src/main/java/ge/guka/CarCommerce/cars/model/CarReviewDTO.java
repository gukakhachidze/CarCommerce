package ge.guka.CarCommerce.cars.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CarReviewDTO {
    private String carModel;
    private String username;
    private Integer rating;
    private String review;
}
