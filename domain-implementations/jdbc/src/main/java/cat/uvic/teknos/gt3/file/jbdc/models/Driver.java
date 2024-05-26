package cat.uvic.teknos.gt3.file.jbdc.models;

import cat.uvic.teknos.gt3.domain.models.Car;
import cat.uvic.teknos.gt3.domain.models.Race;

import java.sql.Date;
import java.util.Set;

public class Driver implements cat.uvic.teknos.gt3.domain.models.Driver {
    private int id;
    private String firstName;
    private String lastName;
    private String nationality;
    private Date birthdate;
    private Set<Car> cars;
    private Set<Race> races;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int driverId) {
        this.id = driverId;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
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
    public Date getBirthdate() {
        return birthdate;
    }

    @Override
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public Set<Car> getCar() {
        return cars;
    }

    @Override
    public void setCar(Set<Car> car) {
        this.cars = car;
    }

    @Override
    public Set<Race> getRace() {
        return races;
    }

    @Override
    public void setRace(Set<Race> race) {
        this.races = race;
    }
}
