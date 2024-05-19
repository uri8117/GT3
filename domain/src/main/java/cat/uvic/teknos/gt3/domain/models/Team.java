package cat.uvic.teknos.gt3.domain.models;

public interface Team {

    int getTeamId();

    void setTeamId(int teamId);

    String getName();

    void setName(String name);

    String getCountry();

    void setCountry(String country);

    int getFoundationYear();

    void setFoundationYear(int foundationYear);

    String getContactInfo();

    void setContactInfo(String contactInfo);
}

