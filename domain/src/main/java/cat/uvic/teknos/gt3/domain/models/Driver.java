package cat.uvic.teknos.gt3.domain.models;

import java.time.LocalDate;
import java.util.Set;

public interface Driver {
    int getId();
    void setId(int id);

    String getFirstName();
    void setFirstName(String firstName);

    String getLastName();
    void setLastName(String lastName);

    String getNationality();
    void setNationality(String nationality);

    LocalDate getBirthdate();
    void setBirthdate(LocalDate birthdate);

    Set<Car> getCars();
    void setCars(Set<Car> cars);

    Set<Race> getRaces();
    void setRaces(Set<Race> races);
}
