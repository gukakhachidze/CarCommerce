package ge.guka.CarCommerce.cars;

import ge.guka.CarCommerce.cars.persistence.Car;
import ge.guka.CarCommerce.cars.user.UserService;
import ge.guka.CarCommerce.cars.user.persistence.AppUser;
import ge.guka.CarCommerce.cars.user.persistence.AppUserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TradeService {

    private final UserService userService;
    private final CarsService carsService;
    private final AppUserRepository appUserRepository;

    @Transactional
    public String buyCar(Long carId){
        AppUser buyer = getCurrentUser();
        Car car = carsService.findCarEntity(carId);

        if (buyer.getBalance().compareTo(car.getPriceInCents()) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        buyer.getOwnedCars().add(car);
        buyer.setBalance(buyer.getBalance() - car.getPriceInCents());
        car.getOwners().add(buyer);
        appUserRepository.save(buyer);

        return buyer.getUsername() + " bought this car: " + car.getModel();

    }

    @Transactional
    public void sellCar(Long carId){
        AppUser user = getCurrentUser();
        Car car = carsService.findCarEntity(carId);

        if (!user.getOwnedCars().contains(car)) {
            throw new RuntimeException("You don't own this car");
        }

        Double sellingPrice = car.getPriceInCents() * 0.8;
        user.setBalance(user.getBalance() + sellingPrice);
        user.getOwnedCars().remove(car);
        car.getOwners().remove(user);
        appUserRepository.save(user);
    }

    private AppUser getCurrentUser(){

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.getUser(username);
    }
}
