package cat.uvic.teknos.gt3.file.Concrete;

import java.io.Serializable;

public class ConcreteTeam extends cat.uvic.teknos.gt3.file.models.Team implements Serializable {

    public ConcreteTeam(int teamId, String name, String country, int foundationYear, String contactInfo) {
        super(teamId, name, country, foundationYear, contactInfo);
    }
}

