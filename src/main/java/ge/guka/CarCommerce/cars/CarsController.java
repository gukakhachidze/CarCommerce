package ge.guka.CarCommerce.cars;

import ge.guka.CarCommerce.cars.model.CarDTO;
import ge.guka.CarCommerce.cars.model.CarRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarsController {
    private final CarsService carsService;

    @GetMapping
    Page<CarDTO> getCars(@RequestParam int page, @RequestParam int pageSize){
       return carsService.getCars(page,pageSize);
    }

    @PostMapping
    ResponseEntity<Void> createCar(@RequestBody @Valid CarRequest request){
        carsService.createCar(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("{id}")
    void updateCar(@RequestParam Long id, @RequestBody @Valid CarRequest request){
        carsService.updateCar(id,request);
    }

    @GetMapping("{id}")
    CarDTO findCar(@PathVariable long id){
        return carsService.findCar(id);
    }

    @DeleteMapping("{id}")
    void deleteCar(@PathVariable Long id){
        carsService.deleteCar(id);
    }
}
