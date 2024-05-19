package cat.uvic.teknos.gt3.domain.models;

public interface Circuit {
    int getCircuitId();

    void setCircuitId(int circuitId);

    String getName();

    void setName(String name);

    String getCountry();

    void setCountry(String country);

    float getLength();

    void setLength(float length);

    String getType();

    void setType(String type);
}
