package ge.guka.CarCommerce.cars.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleValidationError(MethodArgumentNotValidException exception){
        String errorMessage = exception.getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDTO("invalid-request", errorMessage));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDTO> handleNotFoundError(NotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorDTO("not found", exception.getMessage()));
    }

    @ExceptionHandler(InvalidLoginException.class)
    public ResponseEntity<ErrorDTO> handleInvalidLogin(InvalidLoginException exception){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorDTO("invalid-login", exception.getMessage()));
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ErrorDTO> handleInsufficientBalance(InsufficientBalanceException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDTO("insufficient-balance", exception.getMessage()));
    }

    @ExceptionHandler(CarOwnershipException.class)
    public ResponseEntity<ErrorDTO> handleInsufficientBalance(CarOwnershipException exception){
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ErrorDTO("car-ownership", exception.getMessage()));
    }
}
