package cat.uvic.teknos.gt3.file.jbdc.models;

public class Team implements cat.uvic.teknos.gt3.domain.models.Team {
    private int teamId;
    private String name;
    private int brandId;
    private String country;
    private int foundationYear;
    private String contactInfo;

    @Override
    public int getTeamId() {
        return teamId;
    }

    @Override
    public void setTeamId(int teamId) {
        this.teamId = teamId;
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
    public int getBrandId() {
        return brandId;
    }

    @Override
    public void setBrandId(int brandId) {
        this.brandId = brandId;
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
    public int getFoundationYear() {
        return foundationYear;
    }

    @Override
    public void setFoundationYear(int foundationYear) {
        this.foundationYear = foundationYear;
    }

    @Override
    public String getContactInfo() {
        return contactInfo;
    }

    @Override
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}
