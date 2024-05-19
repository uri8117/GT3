package cat.uvic.teknos.gt3.domain.models;

import java.sql.Date;

public interface Race {
    int getRaceId();

    void setRaceId(int id);

    String getName();

    void setName(String name);

    int getCircuitId();

    void setCircuitId(int id);

    Date getDate();

    void setDate(java.sql.Date date);
}
