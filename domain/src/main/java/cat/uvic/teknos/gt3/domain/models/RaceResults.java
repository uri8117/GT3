package cat.uvic.teknos.gt3.domain.models;

public interface RaceResults {
    int getResultId();

    void setResultId(int resultId);

    int getRaceId();

    void setRaceId(int raceId);

    int getTeamId();

    void setTeamId(int teamId);

    int getDriverId();

    void setDriverId(int driverId);

    int getPosition();

    void setPosition(int position);
}
