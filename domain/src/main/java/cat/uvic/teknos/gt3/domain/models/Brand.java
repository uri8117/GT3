package cat.uvic.teknos.gt3.domain.models;

import java.util.Set;

public interface Brand {
    int getId();

    void setId(int id);

    String getUsername();

    void setUsername(String username);

    Set<Car> getCar();

    void setCar(Set<Car> car);

    BrandData getBrandData();

    void setBrandData(BrandData brandData);
}
