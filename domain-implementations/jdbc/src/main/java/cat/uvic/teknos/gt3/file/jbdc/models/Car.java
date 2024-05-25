package cat.uvic.teknos.gt3.file.jbdc.models;

public class Car implements cat.uvic.teknos.gt3.domain.models.Car {
    private int carId;
    private String model;
    private int manufacturingYear;
    private int power;
    private int weight;
    private String engineType;
    private String chassisManufacturer;
    private int teamId;

    @Override
    public int getCarId() {
        return carId;
    }

    @Override
    public void setCarId(int carId) {
        this.carId = carId;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public int getManufacturingYear() {
        return manufacturingYear;
    }

    @Override
    public void setManufacturingYear(int manufacturingYear) {
        this.manufacturingYear = manufacturingYear;
    }

    @Override
    public int getPower() {
        return power;
    }

    @Override
    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String getEngineType() {
        return engineType;
    }

    @Override
    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    @Override
    public String getChassisManufacturer() {
        return chassisManufacturer;
    }

    @Override
    public void setChassisManufacturer(String chassisManufacturer) {
        this.chassisManufacturer = chassisManufacturer;
    }

    @Override
    public int getTeamId() {
        return teamId;
    }

    @Override
    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
}
