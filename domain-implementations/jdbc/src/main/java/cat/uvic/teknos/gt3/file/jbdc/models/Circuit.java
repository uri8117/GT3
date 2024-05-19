package cat.uvic.teknos.gt3.file.jbdc.models;

public class Circuit implements cat.uvic.teknos.gt3.domain.models.Circuit {
    private int circuitId;
    private String name;
    private String country;
    private float length;
    private String type;

    @Override
    public int getCircuitId() {
        return circuitId;
    }

    @Override
    public void setCircuitId(int circuitId) {
        this.circuitId = circuitId;
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
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }
}
