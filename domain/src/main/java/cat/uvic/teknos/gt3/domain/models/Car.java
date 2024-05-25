package cat.uvic.teknos.gt3.domain.models;

import java.util.Set;

public interface Car {
    int getId();

    void setId(int carId);

    String getModelName();

    void setModelName(String modelName);

    Brand getBrand();

    void setBrand(Brand brand);

    CarData getCarData();

    void setCarData(CarData carData);

    Set<Driver> getDriver();

    void setDriver(Set<Driver> driver);
}
