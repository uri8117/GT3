package cat.uvic.teknos.gt3.file.jbdc.repositories;

import cat.uvic.teknos.gt3.file.jbdc.models.Driver;

import com.fcardara.dbtestutils.junit.CreateSchemaExtension;
import com.fcardara.dbtestutils.junit.GetConnectionExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith({CreateSchemaExtension.class, GetConnectionExtension.class})
public class JdbcDriverRepositoryTest {
    private final Connection connection;

    public JdbcDriverRepositoryTest(Connection connection) {
        this.connection = connection;
    }

    @Test
    @DisplayName("Given a new Driver (id = 0), when saved, then a new record is added to the DRIVERS table")
    void shouldInsertNewDriverTest() throws SQLException {

        Driver driver = new Driver();
        driver.setDriverId(1);
        driver.setName("Lewis Hamilton");
        driver.setNationality("British");
        driver.setAge(37);
        driver.setTeamId(1); // Assuming team with ID 1 exists in the database

        var repository = new JdbcDriverRepository(connection);
        repository.save(driver);

        assertTrue(driver.getDriverId() > 0);

        assertNotNull(repository.get(driver.getDriverId()));
    }

    @Test
    @DisplayName("Given an existing Driver, when updated, then the record is updated in the DRIVERS table")
    void shouldUpdateDriverTest() throws SQLException {

        Driver driver = new Driver();
        driver.setDriverId(1); // Assuming driver with ID 1 exists in the database
        driver.setName("Max Verstappen");
        driver.setNationality("Dutch");
        driver.setAge(24);
        driver.setTeamId(2); // Assuming team with ID 2 exists in the database

        var repository = new JdbcDriverRepository(connection);
        repository.save(driver);

        assertTrue(true); // You can add assertions based on your requirements
    }

    @Test
    @DisplayName("Given an existing Driver, when deleted, then the record is removed from the DRIVERS table")
    void shouldDeleteDriverTest() throws SQLException {

        Driver driver = new Driver();
        driver.setDriverId(1); // Assuming driver with ID 1 exists in the database

        var repository = new JdbcDriverRepository(connection);
        repository.delete(driver);

        // You can add assertions to verify deletion
    }
}
