package cat.uvic.teknos.gt3.file.jbdc.models;

import cat.uvic.teknos.gt3.domain.models.Brand;
import cat.uvic.teknos.gt3.domain.models.CarData;
import cat.uvic.teknos.gt3.domain.models.Driver;

import java.util.Set;

public class Car implements cat.uvic.teknos.gt3.domain.models.Car {
    private int id;
    private String modelName;
    private Brand brand;
    private CarData carData;
    private Set<Driver> drivers;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int carId) {
        this.id = carId;
    }

    @Override
    public String getModelName() {
        return modelName;
    }

    @Override
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    @Override
    public Brand getBrand() {
        return brand;
    }

    @Override
    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @Override
    public CarData getCarData() {
        return carData;
    }

    @Override
    public void setCarData(CarData carData) {
        this.carData = carData;
    }

    @Override
    public Set<Driver> getDriver() {
        return drivers;
    }

    @Override
    public void setDriver(Set<Driver> driver) {
        this.drivers = driver;
    }
}
