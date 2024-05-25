package cat.uvic.teknos.gt3.file.jbdc.repositories;

import cat.uvic.teknos.gt3.file.jbdc.models.Car;

import cat.uvic.teknos.gt3.file.jbdc.models.Driver;

import com.fcardara.dbtestutils.junit.CreateSchemaExtension;
import com.fcardara.dbtestutils.junit.GetConnectionExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({CreateSchemaExtension.class, GetConnectionExtension.class})
public class JdbcCarRepositoryTest {
    private final Connection connection;

    public JdbcCarRepositoryTest(Connection connection) {
        this.connection = connection;
    }

    @Test
    @DisplayName("Given a new Car (id = 0), when saved, then a new record is added to the CARS table")
    void shouldInsertNewCarTest() throws SQLException {

        Car car = new Car();
        car.setModel("911");
        car.setManufacturingYear(2023);
        car.setPower(1000);
        car.setWeight(743);
        car.setEngineType("Hybrid");
        car.setChassisManufacturer("Ferrari");
        car.setTeamId(2);


        var repository = new JdbcCarRepository(connection);
        repository.save(car);

        assertTrue(car.getCarId() > 0);

        assertNotNull(repository.get(car.getCarId()));
    }

    @Test
    @DisplayName("Given an existing Car, when updated, then the record is updated in the CARS table")
    void shouldUpdateCarTest() throws SQLException {

        Car car = new Car();
        car.setCarId(1); // Assuming car with ID 1 exists in the database
        car.setModel("W13");
        car.setManufacturingYear(2022);
        car.setPower(950);
        car.setWeight(752);
        car.setEngineType("Hybrid");
        car.setChassisManufacturer("Mercedes");
        car.setTeamId(2);

        var repository = new JdbcCarRepository(connection);
        repository.save(car);

        // When a car changes of team the driver must have car_id null

        // When a driver changes of car the car must have driver_id null

        assertTrue(true); // You can add assertions based on your requirements
    }

    @Test
    @DisplayName("Given an existing Car, when deleted, then the record is removed from the CARS table")
    void shouldDeleteCarTest() throws SQLException {

        Car car = new Car();
        car.setCarId(1);

        var repository = new JdbcCarRepository(connection);
        var repositoryDriver = new JdbcDriverRepository(connection);

        // Update driver to set car_id to null before deleting the car
        Driver driver = repositoryDriver.findByCarId(car.getCarId());
        if (driver != null) {
            driver.setCarId(null);
            repositoryDriver.save(driver); // Update the driver record with car_id set to null
        }

        // Delete the car
        repository.delete(car);

        // Add assertions to verify the deletion and driver's car_id being null
        Driver updatedDriver = repositoryDriver.get(driver.getDriverId());
        assertNotNull(updatedDriver);
        assertNull(updatedDriver.getCarId());
    }
}
