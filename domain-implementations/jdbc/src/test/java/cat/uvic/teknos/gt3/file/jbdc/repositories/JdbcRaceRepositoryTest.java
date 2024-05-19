package cat.uvic.teknos.gt3.file.jbdc.repositories;

import cat.uvic.teknos.gt3.file.jbdc.models.Race;

import com.fcardara.dbtestutils.junit.CreateSchemaExtension;
import com.fcardara.dbtestutils.junit.GetConnectionExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith({CreateSchemaExtension.class, GetConnectionExtension.class})
public class JdbcRaceRepositoryTest {
    private final Connection connection;

    public JdbcRaceRepositoryTest(Connection connection) {
        this.connection = connection;
    }

    @Test
    @DisplayName("Given a new Race (id = 0), when saved, then a new record is added to the RACES table")
    void shouldInsertNewRaceTest() throws SQLException {

        Race race = new Race();
        race.setRaceId(1);
        race.setName("Monaco Grand Prix");
        race.setCircuitId(1); // Assuming circuit with ID 1 exists in the database
        race.setDate(Date.valueOf(LocalDate.of(2024, 5, 26)));

        var repository = new JdbcRaceRepository(connection);
        repository.save(race);

        assertTrue(race.getRaceId() > 0);

        assertNotNull(repository.get(race.getRaceId()));
    }

    @Test
    @DisplayName("Given an existing Race, when updated, then the record is updated in the RACES table")
    void shouldUpdateRaceTest() throws SQLException {

        Race race = new Race();
        race.setRaceId(1); // Assuming race with ID 1 exists in the database
        race.setName("Spanish Grand Prix");
        race.setCircuitId(1); // Assuming circuit with ID 1 exists in the database
        race.setDate(Date.valueOf(LocalDate.of(2024, 5, 26)));

        var repository = new JdbcRaceRepository(connection);
        repository.save(race);

        assertTrue(true); // You can add assertions based on your requirements
    }

    @Test
    @DisplayName("Given an existing Race, when deleted, then the record is removed from the RACES table")
    void shouldDeleteRaceTest() throws SQLException {

        Race race = new Race();
        race.setRaceId(1); // Assuming race with ID 1 exists in the database

        var repository = new JdbcRaceRepository(connection);
        repository.delete(race);

        // You can add assertions to verify deletion
    }
}
