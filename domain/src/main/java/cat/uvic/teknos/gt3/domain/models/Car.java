package cat.uvic.teknos.gt3.domain.models;

public interface Car {
    int getCarId();

    void setCarId(int carId);

    String getBrand();

    void setBrand(String brand);

    String getModel();

    void setModel(String brandModel);

    int getManufacturingYear();

    void setManufacturingYear(int manufacturingYear);

    int getPower();

    void setPower(int power);

    int getWeight();

    void setWeight(int weight);

    String getEngineType();

    void setEngineType(String engineType);

    String getChassisManufacturer();

    void setChassisManufacturer(String chassisManufacturer);
}
