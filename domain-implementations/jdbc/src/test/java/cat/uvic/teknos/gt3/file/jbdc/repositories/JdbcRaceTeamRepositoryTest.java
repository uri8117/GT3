package cat.uvic.teknos.gt3.file.jbdc.repositories;

//import cat.uvic.teknos.gt3.file.jbdc.models.RaceTeam;

import com.fcardara.dbtestutils.junit.CreateSchemaExtension;
import com.fcardara.dbtestutils.junit.GetConnectionExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith({CreateSchemaExtension.class, GetConnectionExtension.class})
public class JdbcRaceTeamRepositoryTest {
    private final Connection connection;

    public JdbcRaceTeamRepositoryTest(Connection connection) {
        this.connection = connection;
    }
    /*
    @Test
    @DisplayName("Given a new Race Team (id = 0), when saved, then a new record is added to the RACE_TEAMS table")
    void shouldInsertNewRaceTeamTest() throws SQLException {

        RaceTeam raceTeam = new RaceTeam();
        raceTeam.setRaceId(1); // Assuming race with ID 1 exists in the database
        raceTeam.setTeamId(1); // Assuming team with ID 1 exists in the database

        var repository = new JdbcRaceTeamRepository(connection);
        repository.save(raceTeam);

        assertNotNull(repository.get(raceTeam.getRaceId(), raceTeam.getTeamId()));
    }

    // Add similar tests for updating and deleting race teams
*/
}
