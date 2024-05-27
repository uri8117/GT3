package cat.uvic.teknos.gt3.file.jbdc.models;

import cat.uvic.teknos.gt3.domain.models.RaceDriverId;

public class RaceDriver implements cat.uvic.teknos.gt3.domain.models.RaceDriver {
    private RaceDriverId id;
    private int position;

    @Override
    public RaceDriverId getId() {
        return id;
    }

    @Override
    public void setId(RaceDriverId id) {
        this.id = id;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public void setPosition(int position) {
        this.position = position;
    }
}
