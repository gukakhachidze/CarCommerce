package ge.guka.CarCommerce.cars.model;

public class CarDTO {
    private long id;
    private String model;
    private int year;
    private boolean driveable;
    private EngineDTO engine;

    public CarDTO(long id, String model, int year, boolean driveable, EngineDTO engine){
        this.id = id;
        this.model = model;
        this.year = year;
        this.driveable = driveable;
        this.engine = engine;
    }

    public long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isDriveable() {
        return driveable;
    }

    public void setDriveable(boolean driveable) {
        this.driveable = driveable;
    }

    public EngineDTO getEngine() {
        return engine;
    }

    public void setEngine(EngineDTO engine) {
        this.engine = engine;
    }
}
