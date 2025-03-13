package ge.guka.CarCommerce.cars.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CarRequest {
    @NotBlank(message = "მოდელის ველი არ უნდა იყოს ცარიელი")
    @Size(max = 20, message = "სიმბოლოების მაქსიმუმი უნდა იყოს 20")
    private String model;
    @Min(value = 1945,message = "მანქანის მინიმუმი წლოვანება უნდა იყოს 1945")
    private int year;
    private boolean driveable;
    //private String imageUrl;
    @Positive(message = "ფასი არ შეიძლება იყოს ნეგატიური რიცხვი")
    private double price;
    @Positive(message = "არასწორ აიდი")
    private Long engineId;
}
