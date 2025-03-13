package ge.guka.CarCommerce.cars;

import ge.guka.CarCommerce.cars.model.CarReviewDTO;
import ge.guka.CarCommerce.cars.model.ReviewRequest;
import ge.guka.CarCommerce.cars.persistence.CarReview;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reviews")
public class CarReviewController {

    private final CarReviewService carReviewService;

    public CarReviewController(CarReviewService carReviewService) {
        this.carReviewService = carReviewService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addReview(@RequestBody ReviewRequest request){

        carReviewService.addReview(request.getCarId(),
                request.getUserId(),
                request.getRating(),
                request.getReview());

        return ResponseEntity.ok("ADDED");
    }

    @GetMapping("/{carId}")
    public ResponseEntity<List<CarReviewDTO>> getReviewsForCar(@PathVariable @Valid Long carId){
        List<CarReview> reviews = carReviewService.getReviewsForCar(carId);

        // საცდელი მაფინგი

        List<CarReviewDTO> reviewDTO = reviews.stream()
                .map(review -> new CarReviewDTO(
                        review.getCar().getModel(),
                        review.getUser().getUsername(),
                        review.getRating(),
                        review.getReview()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(reviewDTO);
    }

}
