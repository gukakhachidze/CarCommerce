package ge.guka.CarCommerce.cars.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarReviewRepository extends JpaRepository<CarReview, Long> {

    List<CarReview> findByCarId(Long carId);

    List<CarReview> findByUserId(Long userId);
}
