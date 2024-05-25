package cat.uvic.teknos.gt3.domain.models;

import java.sql.Date;
import java.util.Set;

public interface Driver {
    int getId();

    void setId(int driverId);

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getNationality();

    void setNationality(String nationality);

    Date getBirthdate();

    void setBirthdate(java.sql.Date birthdate);

    Set<Car> getCar();

    void setCar(Set<Car> car);

    Set<Race> getRace();

    void setRace(Set<Race> race);
}
