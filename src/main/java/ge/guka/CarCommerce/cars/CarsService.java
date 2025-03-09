package ge.guka.CarCommerce.cars;

import ge.guka.CarCommerce.cars.error.NotFoundException;
import ge.guka.CarCommerce.cars.model.CarDTO;
import ge.guka.CarCommerce.cars.model.CarRequest;
import ge.guka.CarCommerce.cars.model.EngineDTO;
import ge.guka.CarCommerce.cars.persistence.Car;
import ge.guka.CarCommerce.cars.persistence.CarRepository;
import ge.guka.CarCommerce.cars.user.UserService;
import ge.guka.CarCommerce.cars.user.persistence.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarsService {

    private final CarRepository carRepository;
    private final EngineService engineService;
    private final UserService userService;

    public Page<CarDTO> getCars(int page, int pageSize) {
        return carRepository.findCars(
                PageRequest.of(page,pageSize));
    }

    public CarDTO findCar(long id) {
        Car car = carRepository.findById(id).orElseThrow(() -> buildNotFoundException(id));
        return mapCar(car);
    }

    public void createCar(CarRequest request) {
        Car car = new Car();
        car.setModel(request.getModel());
        car.setYear(request.getYear());
        car.setDriveable(request.isDriveable());
        car.setImageUrl(request.getImageUrl());
        car.setPriceInCents(request.getPrice());
        car.setEngine(engineService.findEngine(request.getEngineId()));
        carRepository.save(car);
    }

    public void updateCar(Long id, CarRequest request) {
        Car car = carRepository.findById(id).orElseThrow(() -> buildNotFoundException(id));
        car.setModel(request.getModel());
        car.setYear(request.getYear());
        car.setDriveable(request.isDriveable());
        car.setImageUrl(request.getImageUrl());
        car.setPriceInCents(request.getPrice());
        if (car.getEngine().getId() != request.getEngineId()){
            car.setEngine(engineService.findEngine(request.getEngineId()));
        }
        carRepository.save(car);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    private CarDTO mapCar(Car car) {
        return new CarDTO(car.getId(),car.getModel(),car.getYear(),car.isDriveable(),car.getImageUrl(),car.getPriceInCents(),
                new EngineDTO(
                        car.getEngine().getId(),
                        car.getEngine().getHorsePower(),
                        car.getEngine().getCapacity()
                ));
    }

    private NotFoundException buildNotFoundException(Long id){
        return new NotFoundException("Car with id " + id + " not found");
    }

    public Car findCarEntity(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + id));
    }
}
