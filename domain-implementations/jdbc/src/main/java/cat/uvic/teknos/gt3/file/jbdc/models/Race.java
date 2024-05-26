package cat.uvic.teknos.gt3.file.jbdc.models;

import cat.uvic.teknos.gt3.domain.models.Circuit;
import cat.uvic.teknos.gt3.domain.models.Driver;

import java.sql.Date;
import java.util.Set;

public class Race implements cat.uvic.teknos.gt3.domain.models.Race {
    private int id;
    private String raceName;
    private Date raceDate;
    private Set<Driver> drivers;
    private Circuit circuit;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getRaceName() {
        return raceName;
    }

    @Override
    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    @Override
    public Date getRaceDate() {
        return raceDate;
    }

    @Override
    public void setRaceDate(Date raceDate) {
        this.raceDate = raceDate;
    }

    @Override
    public Set<Driver> getDriver() {
        return drivers;
    }

    @Override
    public void setDriver(Set<Driver> driver) {
        this.drivers = driver;
    }

    @Override
    public Circuit getCircuit() {
        return circuit;
    }

    @Override
    public void setCircuit(Circuit circuit) {
        this.circuit = circuit;
    }
}
