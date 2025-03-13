package ge.guka.CarCommerce.cars;

import ge.guka.CarCommerce.cars.error.NotFoundException;
import ge.guka.CarCommerce.cars.persistence.Car;
import ge.guka.CarCommerce.cars.persistence.CarRepository;
import ge.guka.CarCommerce.cars.persistence.CarReview;
import ge.guka.CarCommerce.cars.persistence.CarReviewRepository;
import ge.guka.CarCommerce.cars.user.persistence.AppUser;
import ge.guka.CarCommerce.cars.user.persistence.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarReviewService {

    private final CarReviewRepository carReviewRepository;
    private final CarRepository carRepository;
    private final AppUserRepository appUserRepository;

    public CarReview addReview(Long carId, Long userId, int rating, String reviewText){
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new NotFoundException("Car with " + carId + " not found"));

        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("user with " + userId + " not found"));

        CarReview carReview = new CarReview();
        carReview.setCar(car);
        carReview.setUser(user);
        carReview.setRating(rating);
        carReview.setReview(reviewText);

        carReviewRepository.save(carReview);

        return carReview;

    }

    public List<CarReview> getReviewsForCar(Long carId){
        return carReviewRepository.findByCarId(carId);
    }

    public List<CarReview> getReviewsByUser(Long userId){
        return carReviewRepository.findByUserId(userId);
    }
}
