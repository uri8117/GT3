package cat.uvic.teknos.gt3.domain.models;

import java.util.Set;

public interface Circuit {
    int getId();

    void setId(int id);

    String getCircuitName();

    void setCircuitName(String circuitName);

    String getCountry();

    void setCountry(String country);

    float getLength();

    void setLength(float length);

    Set<Race> getRace();

    void setRace(Set<Race> race);
}
