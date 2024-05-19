package cat.uvic.teknos.gt3.file.models;

import java.io.Serializable;

public class Team implements cat.uvic.teknos.gt3.domain.models.Team, Serializable{
    private int teamId;
    private String name;
    private String country;
    private int foundationYear;
    private String contactInfo;

    public Team(int teamId, String name, String country, int foundationYear, String contactInfo) {
        this.teamId = teamId;
        this.name = name;
        this.country = country;
        this.foundationYear = foundationYear;
        this.contactInfo = contactInfo;
    }

    public Team() {
    }


    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getFoundationYear() {
        return foundationYear;
    }

    public void setFoundationYear(int foundationYear) {
        this.foundationYear = foundationYear;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}

