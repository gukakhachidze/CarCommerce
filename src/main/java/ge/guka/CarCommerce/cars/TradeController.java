package ge.guka.CarCommerce.cars;

import ge.guka.CarCommerce.cars.model.CarDTO;
import ge.guka.CarCommerce.cars.persistence.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static ge.guka.CarCommerce.security.AuthorizationConstants.USER_OR_ADMIN;

@RestController
@RequestMapping("/trade")
@PreAuthorize(USER_OR_ADMIN)
public class TradeController {

    private final TradeService tradeService;

    @Autowired
    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }


    @PostMapping("/sell/{carId}")
    ResponseEntity<String> sellCar(@PathVariable Long carId){

        tradeService.sellCar(carId);

        return ResponseEntity.ok("The car was successfully sold");
    }

    @PostMapping("/buy/{carId}")
    ResponseEntity<String> buyCar(@PathVariable Long carId){
        String message = tradeService.buyCar(carId);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/see")
    List<CarDTO> getUserCars(){
        return tradeService.getCurrentUserCars();
    }

}
