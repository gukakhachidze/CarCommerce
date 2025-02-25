package ge.guka.CarCommerce.cars;

import ge.guka.CarCommerce.cars.model.CarDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    @GetMapping("{id}")
    CarDTO findCar(@PathVariable long id){
        return carsService.findCar(id);
    }

    @DeleteMapping("{id}")
    void deleteCar(@PathVariable Long id){
        carsService.deleteCar(id);
    }
}
