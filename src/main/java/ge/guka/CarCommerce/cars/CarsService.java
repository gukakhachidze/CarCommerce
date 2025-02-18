package ge.guka.CarCommerce.cars;

import ge.guka.CarCommerce.cars.model.CarDTO;
import ge.guka.CarCommerce.cars.model.CarRequest;
import ge.guka.CarCommerce.cars.model.EngineDTO;
import ge.guka.CarCommerce.cars.persistence.Car;
import ge.guka.CarCommerce.cars.persistence.CarRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CarsService {

    private final CarRepository carRepository;
    private final EngineService engineService;

    public CarsService(CarRepository carRepository, EngineService engineService) {
        this.carRepository = carRepository;
        this.engineService = engineService;
    }

    public Page<CarDTO> getCars(int page, int pageSize) {
        return carRepository.findCars(
                PageRequest.of(page,pageSize));
    }

    public CarDTO findCar(long id) {
        Car car = carRepository.findById(id).get();
        return mapCar(car);
    }

    public void addCar(CarRequest request) {
        Car car = new Car();
        car.setModel(request.getModel());
        car.setYear(request.getYear());
        car.setDrivable(request.isDriveable());
        car.setEngine(engineService.findEngine(request.getEngineId()));
        carRepository.save(car);
    }

    public void updateCar(Long id, CarRequest request) {
        Car car = carRepository.findById(id).get();
        car.setModel(request.getModel());
        car.setYear(request.getYear());
        car.setDrivable(request.isDriveable());
        if (car.getEngine().getId() != request.getEngineId()){
            car.setEngine(engineService.findEngine(request.getEngineId()));
        }
        carRepository.save(car);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    private CarDTO mapCar(Car car) {
        return new CarDTO(car.getId(), car.getModel(), car.getYear(), car.isDrivable(),
                new EngineDTO(
                        car.getEngine().getId(),
                        car.getEngine().getHorsePower(),
                        car.getEngine().getCapacity()
                )
        );
    }
}
