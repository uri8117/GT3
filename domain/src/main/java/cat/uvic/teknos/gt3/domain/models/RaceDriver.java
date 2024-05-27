package cat.uvic.teknos.gt3.domain.models;public interface RaceDriver {
    RaceDriverId getId();

    void setId(RaceDriverId id);

    Driver getDriver();
    void setDriver(Driver driver);

    Race getRace();
    void setRace(Race race);

    int getPosition();

    void setPosition(int position);
}
