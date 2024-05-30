package cat.uvic.teknos.gt3.domain.models;

public interface Circuit {
    int getId();
    void setId(int id);

    String getCircuitName();
    void setCircuitName(String circuitName);

    String getCountry();
    void setCountry(String country);

    double getLengthKm();
    void setLengthKm(double lengthKm);
}
