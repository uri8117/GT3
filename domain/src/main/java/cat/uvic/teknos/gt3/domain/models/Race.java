package cat.uvic.teknos.gt3.domain.models;

import java.sql.Date;
import java.util.Set;

public interface Race {
    int getId();

    void setId(int id);

    String getRaceName();

    void setRaceName(String raceName);

    Date getRaceDate();

    void setRaceDate(java.sql.Date raceDate);

    Set<Driver> getDrivers();

    void setDrivers(Set<Driver> driver);

    Circuit getCircuit();

    void setCircuit(Circuit circuit);
}
