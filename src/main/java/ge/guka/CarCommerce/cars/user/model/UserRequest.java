package ge.guka.CarCommerce.cars.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
@AllArgsConstructor
public class UserRequest {
    @NotBlank(message = "იუზერის სახელი არ შეიძლება იყოს ნალი ან ცარიელი")
    @Size(min = 5, max = 20, message = "იუზერის სახელი უნდა იყოს მინ:5 და მაქს:20")
    private String username;

    @NotBlank(message = "პაროლი არ შეიძლება იყოს ცარიელი ან ნალი")
    @Size(min = 8, message = "პაროლი მინიმუმ 8 სიმბოლოსგან უნდა შედგებოდეს")
    private String password;
    @Min(value = 0, message = "ბალანსი არ უნდა იყოს უარყოფითი რიცხვი")
    @JsonProperty("balance")
    private Double balance;

    @NotEmpty(message = "როლის აიდი არ შეიძლება იყოს ცარიელი")
    private Set<Long> roleIds;
}
