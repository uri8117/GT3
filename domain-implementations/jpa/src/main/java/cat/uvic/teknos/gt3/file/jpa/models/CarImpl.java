package cat.uvic.teknos.gt3.file.jpa.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

import cat.uvic.teknos.gt3.domain.models.Brand;
import cat.uvic.teknos.gt3.domain.models.Car;
import cat.uvic.teknos.gt3.domain.models.Driver;

@Entity
@Table(name = "CAR")
public class CarImpl implements Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CAR")
    private int id;

    @ManyToOne(targetEntity = BrandImpl.class)
    @JoinColumn(name = "ID_BRAND", nullable = false)
    private Brand brand;

    @Column(name = "MODEL_NAME", nullable = false)
    private String modelName;

    @OneToOne(mappedBy = "car", cascade = CascadeType.ALL, targetEntity = CarDataImpl.class)
    @PrimaryKeyJoinColumn
    private cat.uvic.teknos.gt3.domain.models.CarData carData;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity = CarImpl.class)
    @JoinTable(name = "CAR_DRIVER", joinColumns = @JoinColumn(name = "ID_CAR"), inverseJoinColumns = @JoinColumn(name = "ID_DRIVER"))
    private Set<Driver> drivers = new HashSet<>();

    // Getters and setters

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Brand getBrand() {
        return brand;
    }

    @Override
    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @Override
    public String getModelName() {
        return modelName;
    }

    @Override
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    @Override
    public cat.uvic.teknos.gt3.domain.models.CarData getCarData() {
        return carData;
    }

    @Override
    public void setCarData(cat.uvic.teknos.gt3.domain.models.CarData carData) {
        this.carData = carData;
        if (carData != null) {
            carData.setCar(this);
        }
    }

    @Override
    public Set<Driver> getDrivers() {
        return drivers;
    }

    @Override
    public void setDrivers(Set<Driver> drivers) {
        this.drivers = drivers;
    }
}
