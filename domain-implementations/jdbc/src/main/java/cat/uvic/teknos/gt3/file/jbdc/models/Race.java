package cat.uvic.teknos.gt3.file.jbdc.models;

import java.sql.Date;

public class Race implements cat.uvic.teknos.gt3.domain.models.Race {
    private int raceId;
    private String name;
    private int circuitId;
    private java.sql.Date date;

    @Override
    public int getRaceId() {
        return raceId;
    }

    @Override
    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getCircuitId() {
        return circuitId;
    }

    @Override
    public void setCircuitId(int circuitId) {
        this.circuitId = circuitId;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public void setDate(java.sql.Date date) {
        this.date = date;
    }
}
