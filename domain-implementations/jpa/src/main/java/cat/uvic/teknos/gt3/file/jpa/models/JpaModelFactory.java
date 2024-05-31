package cat.uvic.teknos.gt3.file.jpa.models;


import cat.uvic.teknos.gt3.domain.models.Brand;
import cat.uvic.teknos.gt3.domain.models.BrandData;
import cat.uvic.teknos.gt3.domain.models.Car;
import cat.uvic.teknos.gt3.domain.models.Circuit;
import cat.uvic.teknos.gt3.domain.models.Driver;
import cat.uvic.teknos.gt3.domain.models.ModelFactory;
import cat.uvic.teknos.gt3.domain.models.Race;

public class JpaModelFactory implements ModelFactory {
    @Override
    public Brand createBrand() {
        return new cat.uvic.teknos.gt3.file.jpa.models.BrandImpl();
    }

    @Override
    public BrandData createBrandData() {
        return new cat.uvic.teknos.gt3.file.jpa.models.BrandDataImpl();
    }

    @Override
    public Car createCar() {
        return new cat.uvic.teknos.gt3.file.jpa.models.CarImpl();
    }

    @Override
    public Circuit createCircuit() {
        return new cat.uvic.teknos.gt3.file.jpa.models.CircuitImpl();
    }

    @Override
    public Driver createDriver() {
        return new cat.uvic.teknos.gt3.file.jpa.models.DriverImpl();
    }

    @Override
    public Race createRace() {
        return new cat.uvic.teknos.gt3.file.jpa.models.RaceImpl();
    }
}
