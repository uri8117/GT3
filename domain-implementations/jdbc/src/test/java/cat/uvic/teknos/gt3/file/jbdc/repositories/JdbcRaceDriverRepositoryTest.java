package cat.uvic.teknos.gt3.file.jbdc.repositories;

//import cat.uvic.teknos.gt3.file.jbdc.models.RaceDriver;

import com.fcardara.dbtestutils.junit.CreateSchemaExtension;
import com.fcardara.dbtestutils.junit.GetConnectionExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith({CreateSchemaExtension.class, GetConnectionExtension.class})
public class JdbcRaceDriverRepositoryTest {
    private final Connection connection;

    public JdbcRaceDriverRepositoryTest(Connection connection) {
        this.connection = connection;
    }
    /*
    @Test
    @DisplayName("Given a new Race Driver (id = 0), when saved, then a new record is added to the RACE_DRIVERS table")
    void shouldInsertNewRaceDriverTest() throws SQLException {

        RaceDriver raceDriver = new RaceDriver();
        raceDriver.setRaceId(1); // Assuming race with ID 1 exists in the database
        raceDriver.setDriverId(1); // Assuming driver with ID 1 exists in the database

        var repository = new JdbcRaceDriverRepository(connection);
        repository.save(raceDriver);

        assertNotNull(repository.get(raceDriver.getRaceId(), raceDriver.getDriverId()));
    }

    // Add similar tests for updating and deleting race drivers
*/
}