package cat.uvic.teknos.gt3.file.jbdc.models;

import cat.uvic.teknos.gt3.domain.models.Race;

import java.util.Set;

public class Circuit implements cat.uvic.teknos.gt3.domain.models.Circuit {
    private int id;
    private String circuitName;
    private String country;
    private float length;
    private Set<Race> races;
    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getCircuitName() {
        return circuitName;
    }

    @Override
    public void setCircuitName(String circuitName) {
        this.circuitName = circuitName;
    }

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public float getLength() {
        return length;
    }

    @Override
    public void setLength(float length) {
        this.length = length;
    }

    @Override
    public Set<Race> getRace() {
        return races;
    }

    @Override
    public void setRace(Set<Race> race) {
        this.races = race;
    }
}
