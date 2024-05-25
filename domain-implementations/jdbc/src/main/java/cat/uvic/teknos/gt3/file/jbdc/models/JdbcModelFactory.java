package cat.uvic.teknos.gt3.file.jbdc.models;

import cat.uvic.teknos.gt3.domain.models.*;
import cat.uvic.teknos.gt3.domain.models.Circuit;
import cat.uvic.teknos.gt3.domain.models.Driver;

public class JdbcModelFactory implements ModelFactory {
    @Override
    public Team createTeam() {
        return new cat.uvic.teknos.gt3.file.jbdc.models.Team();
    }

    @Override
    public Circuit createCircuit() {
        return new cat.uvic.teknos.gt3.file.jbdc.models.Circuit();
    }

    @Override
    public Driver createDriver() {
        return new cat.uvic.teknos.gt3.file.jbdc.models.Driver();
    }

    @Override
    public Car createCar() {
        return new cat.uvic.teknos.gt3.file.jbdc.models.Car();
    }

    public Brand createBrand() {
        return new cat.uvic.teknos.gt3.file.jbdc.models.Brand();
    }

    @Override
    public Race createRace() {
        return new cat.uvic.teknos.gt3.file.jbdc.models.Race();
    }

    @Override
    public RaceTeam createRaceTeam() {
        return null;
    }

    @Override
    public RaceDriver createRaceDriver() {
        return null;
    }

    @Override
    public RaceResults createRaceResults() {
        return null;
    }

}
