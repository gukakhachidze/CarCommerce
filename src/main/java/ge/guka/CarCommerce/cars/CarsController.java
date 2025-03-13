package ge.guka.CarCommerce.cars;

import ge.guka.CarCommerce.cars.model.CarDTO;
import ge.guka.CarCommerce.cars.model.CarRequest;
import ge.guka.CarCommerce.cars.model.ImageUpdateRequest;
import ge.guka.CarCommerce.cars.persistence.Car;
import ge.guka.CarCommerce.cars.user.persistence.AppUser;
import ge.guka.CarCommerce.s3.service.StorageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static ge.guka.CarCommerce.security.AuthorizationConstants.ADMIN;
import static ge.guka.CarCommerce.security.AuthorizationConstants.USER_OR_ADMIN;


@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarsController {
    private final CarsService carsService;
    private final StorageService storageService;

    @GetMapping
    @PreAuthorize(USER_OR_ADMIN)
    Page<CarDTO> getCars(@RequestParam int page, @RequestParam int pageSize){
       return carsService.getCars(page,pageSize);
    }

    @PostMapping
    @PreAuthorize(ADMIN)
    ResponseEntity<Void> createCar(@RequestBody @Valid CarRequest request){
        carsService.createCar(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("{id}")
    @PreAuthorize(ADMIN)
    void updateCar(@PathVariable Long id, @RequestBody @Valid CarRequest request){
        carsService.updateCar(id,request);
    }

    @GetMapping("{id}")
    @PreAuthorize(USER_OR_ADMIN)
    CarDTO findCar(@PathVariable long id){
        return carsService.findCar(id);
    }

    @DeleteMapping("{id}")
    @PreAuthorize(ADMIN)
    void deleteCar(@PathVariable Long id){
        carsService.deleteCar(id);
    }

//    @PostMapping("/upload/{carId}")
//    public ResponseEntity<String> uploadCarImage(@PathVariable Long carId, @RequestParam MultipartFile file){
//        Car car = carsService.findCarEntity(carId);
//
//        //String imgKey = "cars/" + carId + "/" + file.getOriginalFilename();
//        String imgKey = "cars/" + file.getOriginalFilename();
//
//        String imageUrl = storageService.uploadFile(imgKey, file);
//
//        car.setImageUrl(imageUrl);
//        carsService.saveChanges(car);
//
//        return ResponseEntity.ok(imageUrl);
//    }

    @PatchMapping("/image/{carId}")
    @PreAuthorize(ADMIN)
    public ResponseEntity<String> getCarImageUrl(@PathVariable Long carId, @RequestBody ImageUpdateRequest request) {
        carsService.updateImage(carId, request.getImageUrl());
        return ResponseEntity.ok("updated");
    }
}
