package cat.uvic.teknos.gt3.file.jpa.models;

import jakarta.persistence.*;

import cat.uvic.teknos.gt3.domain.models.Circuit;

@Entity
@Table(name = "CIRCUIT")
public class CircuitImpl implements Circuit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CIRCUIT")
    private int id;

    @Column(name = "CIRCUIT_NAME", nullable = false, unique = true)
    private String circuitName;

    @Column(name = "COUNTRY", nullable = false)
    private String country;

    @Column(name = "LENGTH_KM", nullable = false)
    private double lengthKm;

    // Getters and setters

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
    public double getLengthKm() {
        return lengthKm;
    }

    @Override
    public void setLengthKm(double lengthKm) {
        this.lengthKm = lengthKm;
    }
}
