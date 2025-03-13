package ge.guka.CarCommerce.cars;

import ge.guka.CarCommerce.cars.error.CarOwnershipException;
import ge.guka.CarCommerce.cars.error.InsufficientBalanceException;
import ge.guka.CarCommerce.cars.model.CarDTO;
import ge.guka.CarCommerce.cars.model.EngineDTO;
import ge.guka.CarCommerce.cars.persistence.Car;
import ge.guka.CarCommerce.cars.user.UserService;
import ge.guka.CarCommerce.cars.user.persistence.AppUser;
import ge.guka.CarCommerce.cars.user.persistence.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TradeService {

    private final UserService userService;
    private final CarsService carsService;
    private final AppUserRepository appUserRepository;

    public String buyCar(Long carId){
        AppUser buyer = getCurrentUser();
        Car car = carsService.findCarEntity(carId);

        if (buyer.getBalance().compareTo(car.getPriceInCents()) < 0) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        buyer.getOwnedCars().add(car);
        buyer.setBalance(buyer.getBalance() - car.getPriceInCents());
        car.getOwners().add(buyer);
        appUserRepository.save(buyer);

        return buyer.getUsername() + " bought this car: " + car.getModel();

    }

    public void sellCar(Long carId){
        AppUser user = getCurrentUser();
        Car car = carsService.findCarEntity(carId);

        if (!user.getOwnedCars().contains(car)) {
            throw new CarOwnershipException("You don't own this car");
        }

        Double sellingPrice = car.getPriceInCents() * 0.8;
        user.setBalance(user.getBalance() + sellingPrice);
        user.getOwnedCars().remove(car);
        car.getOwners().remove(user);
        appUserRepository.save(user);
    }

    public List<CarDTO> getCurrentUserCars() {
        AppUser user = getCurrentUser();
        return user.getOwnedCars()
                .stream()
                .map(car -> new CarDTO(car.getId(), car.getModel(), car.getYear(), car.isDriveable(), car.getImageUrl(), car.getPriceInCents(),
                        new EngineDTO(car.getEngine().getId(), car.getEngine().getHorsePower(), car.getEngine().getCapacity())))
                .collect(Collectors.toList());
    }

    private AppUser getCurrentUser(){

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.getUser(username);
    }
}
