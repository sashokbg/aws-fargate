package bg.alex.ecs.backen;

public class Car {
    private int carId;
    private String brand;

    public Car(int carId, String brand) {
        this.carId = carId;
        this.brand = brand;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
