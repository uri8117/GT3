package cat.uvic.teknos.gt3.file.jpa.models;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import cat.uvic.teknos.gt3.domain.models.Car;
import cat.uvic.teknos.gt3.domain.models.Driver;
import cat.uvic.teknos.gt3.domain.models.Race;

@Entity
@Table(name = "DRIVER")
public class DriverImpl implements Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DRIVER")
    private int id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "NATIONALITY", nullable = false)
    private String nationality;

    @Column(name = "BIRTHDATE", nullable = false)
    private Date birthdate;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity = CarImpl.class)
    @JoinTable(name = "CAR_DRIVER", joinColumns = @JoinColumn(name = "ID_DRIVER"), inverseJoinColumns = @JoinColumn(name = "ID_CAR"))
    private Set<Car> cars = new HashSet<>();

    @ManyToMany(mappedBy = "raceDrivers", targetEntity = RaceImpl.class)
    private Set<Race> races = new HashSet<>();

    // Getters and setters

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
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
    public Set<Car> getCars() {
        return cars;
    }

    @Override
    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    @Override
    public Set<Race> getRaces() {
        return races;
    }

    @Override
    public void setRaces(Set<Race> races) {
        this.races = races;
    }
}
