package cat.uvic.teknos.gt3.file.jbdc.models;

public class Driver implements cat.uvic.teknos.gt3.domain.models.Driver {
    private int driverId;
    private String name;
    private String nationality;
    private int age;
    private Integer carId;
    private int teamId;

    @Override
    public int getDriverId() {
        return driverId;
    }

    @Override
    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getNationality() {
        return nationality;
    }

    @Override
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public Integer getCarId() {
        return carId;
    }

    @Override
    public void setCarId(Integer carId) {
        this.carId = carId;
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
