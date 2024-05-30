package cat.uvic.teknos.gt3.file.jpa.models;

import jakarta.persistence.*;

import cat.uvic.teknos.gt3.domain.models.Car;
import cat.uvic.teknos.gt3.domain.models.CarData;

@Entity
@Table(name = "CAR_DATA")
public class CarDataImpl implements CarData {
    @Id
    @Column(name = "ID_CAR")
    private int id;

    @Column(name = "HORSEPOWER", nullable = false)
    private int horsepower;

    @Column(name = "WEIGHT", nullable = false)
    private int weight;

    @OneToOne(targetEntity = CarImpl.class)
    @MapsId
    @JoinColumn(name = "ID_CAR")
    private Car car;

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
    public int getHorsepower() {
        return horsepower;
    }

    @Override
    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public Car getCar() {
        return car;
    }

    @Override
    public void setCar(Car car) {
        this.car = car;
    }
}
