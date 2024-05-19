package cat.uvic.teknos.gt3.file.jbdc.repositories;

//import cat.uvic.teknos.gt3.file.jbdc.models.RaceResult;

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
public class JdbcRaceResultRepositoryTest {
    private final Connection connection;

    public JdbcRaceResultRepositoryTest(Connection connection) {
        this.connection = connection;
    }
    /*
    @Test
    @DisplayName("Given a new Race Result (id = 0), when saved, then a new record is added to the RACE_RESULTS table")
    void shouldInsertNewRaceResultTest() throws SQLException {

        RaceResult raceResult = new RaceResult();
        raceResult.setResultId(1);
        raceResult.setRaceId(1); // Assuming race with ID 1 exists in the database
        raceResult.setTeamId(1); // Assuming team with ID 1 exists in the database
        raceResult.setDriverId(1); // Assuming driver with ID 1 exists in the database
        raceResult.setPosition(1);

        var repository = new JdbcRaceResultRepository(connection);
        repository.save(raceResult);

        assertNotNull(repository.get(raceResult.getResultId()));
    }

    // Add similar tests for updating and deleting race results
    */
}
