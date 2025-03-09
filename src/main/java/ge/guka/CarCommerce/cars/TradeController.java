package ge.guka.CarCommerce.cars;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trade")
public class TradeController {

    private final TradeService tradeService;

    @Autowired
    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }


    @PostMapping("/sell/{carId}")
    ResponseEntity<String> sellCar(@PathVariable Long carId){

        return ResponseEntity.ok("Ok");
    }

    @PostMapping("/buy/{carId}")
    ResponseEntity<String> buyCar(@PathVariable Long carId){
        String low = tradeService.buyCar(carId);
        return ResponseEntity.ok(low);
    }

}
