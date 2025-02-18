package ge.guka.CarCommerce.cars.model;

public class EngineRequest {
    private int horsePower;
    private double capacity;

    public EngineRequest(int horsePower, double capacity) {
        this.horsePower = horsePower;
        this.capacity = capacity;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }
}
