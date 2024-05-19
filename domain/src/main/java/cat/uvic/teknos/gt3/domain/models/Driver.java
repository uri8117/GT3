package cat.uvic.teknos.gt3.domain.models;

public interface Driver {
    int getDriverId();

    void setDriverId(int driverId);

    String getName();

    void setName(String name);

    String getNationality();

    void setNationality(String nationality);

    int getAge();

    void setAge(int age);

    int getTeamId();

    void setTeamId(int teamId);
}
