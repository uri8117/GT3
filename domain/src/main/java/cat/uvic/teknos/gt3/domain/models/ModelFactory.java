package cat.uvic.teknos.gt3.domain.models;

public interface ModelFactory {

    Team createTeam();

    Circuit createCircuit();

    Driver createDriver();

    Car createCar();

    Brand createBrand();

    CarModel createCarModel();

    Race createRace();

    RaceTeam createRaceTeam();

    RaceDriver createRaceDriver();

    RaceResults createRaceResults();
}
