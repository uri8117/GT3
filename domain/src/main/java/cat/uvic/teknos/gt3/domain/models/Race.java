package cat.uvic.teknos.gt3.domain.models;

import java.time.LocalDate;
import java.util.Set;

public interface Race {
    int getId();
    void setId(int id);

    String getRaceName();
    void setRaceName(String raceName);

    LocalDate getRaceDate();
    void setRaceDate(LocalDate raceDate);

    Circuit getCircuit();
    void setCircuit(Circuit circuit);

    Set<RaceDriver> getRaceDrivers();
    void setRaceDrivers(Set<RaceDriver> raceDrivers);
}
