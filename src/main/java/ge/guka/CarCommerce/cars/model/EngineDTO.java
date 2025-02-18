package ge.guka.CarCommerce.cars.model;

public class EngineDTO {
    private long id;
    private int horsePower;
    private double capacity;

    public EngineDTO(long id, int horsePower, double capacity){
        this.id = id;
        this.horsePower = horsePower;
        this.capacity = capacity;
    }

    public long getId(){
        return id;
    }

    public int getHorsePower(){
        return horsePower;
    }

    public void setHorsePower(int horsePower){
        this.horsePower = horsePower;
    }

    public double getCapacity(){
        return capacity;
    }

    public void setCapacity(double capacity){
        this.capacity = capacity;
    }

    public void printDetails(){
        System.out.println("Engine HP: " + horsePower);
        System.out.println("Engine Capacity: " + capacity);
    }
}
