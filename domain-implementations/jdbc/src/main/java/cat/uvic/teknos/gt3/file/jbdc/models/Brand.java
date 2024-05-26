package cat.uvic.teknos.gt3.file.jbdc.models;

import cat.uvic.teknos.gt3.domain.models.BrandData;
import cat.uvic.teknos.gt3.domain.models.Car;

import java.util.Set;

public class Brand implements cat.uvic.teknos.gt3.domain.models.Brand {
    private int id;
    private String name;
    private Set<Car> cars;
    private BrandData brandData;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
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
    public Set<Car> getCar() {
        return cars;
    }

    @Override
    public void setCar(Set<Car> car) {
        this.cars = car;
    }

    @Override
    public BrandData getBrandData() {
        return brandData;
    }

    @Override
    public void setBrandData(BrandData brandData) {
        this.brandData = brandData;
    }
}
