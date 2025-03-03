package ge.guka.CarCommerce.cars.error;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String message){
        super(message);
    }
}
