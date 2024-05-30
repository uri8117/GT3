package cat.uvic.teknos.gt3.file.jpa.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

import cat.uvic.teknos.gt3.domain.models.Brand;
import cat.uvic.teknos.gt3.domain.models.BrandData;
import cat.uvic.teknos.gt3.domain.models.Car;

@Entity
@Table(name = "BRAND")
public class BrandImpl implements Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_BRAND")
    private int id;

    @Column(name = "BRAND_NAME", nullable = false, unique = true)
    private String brandName;

    @OneToOne(mappedBy = "brand", cascade = CascadeType.ALL, targetEntity = BrandDataImpl.class)
    @PrimaryKeyJoinColumn
    private BrandData brandData;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, targetEntity = CarImpl.class)
    private Set<Car> cars = new HashSet<>();

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
    public String getBrandName() {
        return brandName;
    }

    @Override
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @Override
    public BrandData getBrandData() {
        return brandData;
    }

    @Override
    public void setBrandData(BrandData brandData) {
        this.brandData = brandData;
        if (brandData != null) {
            brandData.setBrand(this);
        }
    }

    @Override
    public Set<Car> getCars() {
        return cars;
    }

    @Override
    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }
}
