package ge.guka.CarCommerce.cars;

import ge.guka.CarCommerce.cars.persistence.Car;
import ge.guka.CarCommerce.cars.user.persistence.AppUser;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class TradeServiceTest {

    //private final CarsService carsService;

    @Test
    void testBuyCar(){
        Long carId = 1L;
        Car car = new Car();
        car.setId(carId);
        car.setPriceInCents(5000D);

        AppUser user = new AppUser();
        user.setBalance(10000D);

        // when(carsService.findCar(carId)).thenReturn(car);
    }
}
