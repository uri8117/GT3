package cat.uvic.teknos.gt3.domain.models;

import java.util.Set;

public interface Brand {
    int getId();

    void setId(int id);

    String getName();

    void setName(String name);

    Set<Car> getCar();

    void setCar(Set<Car> car);

    BrandData getBrandData();

    void setBrandData(BrandData brandData);
}
