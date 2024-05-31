package cat.uvic.teknos.gt3.file.jpa.models;

import jakarta.persistence.*;

import cat.uvic.teknos.gt3.domain.models.Driver;
import cat.uvic.teknos.gt3.domain.models.Race;
import cat.uvic.teknos.gt3.domain.models.RaceDriver;
import cat.uvic.teknos.gt3.domain.models.RaceDriverId;

@Entity
@Table(name = "RACE_DRIVER")
public class RaceDriverImpl implements RaceDriver {
    @EmbeddedId
    private RaceDriverIdImpl id;

    @Column(name = "POSITION", nullable = false)
    private int position;

    @ManyToOne(targetEntity = RaceImpl.class)
    @MapsId("raceId")
    @JoinColumn(name = "ID_RACE")
    private Race race;

    @ManyToOne(targetEntity = DriverImpl.class)
    @MapsId("driverId")
    @JoinColumn(name = "ID_DRIVER")
    private Driver driver;

    // Getters and setters

    @Override
    public RaceDriverId getId() {
        return id;
    }

    @Override
    public void setId(RaceDriverId id) {
        this.id = (RaceDriverIdImpl) id;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public Race getRace() {
        return race;
    }

    @Override
    public void setRace(Race race) {
        this.race = race;
    }

    @Override
    public Driver getDriver() {
        return driver;
    }

    @Override
    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
