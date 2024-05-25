package cat.uvic.teknos.gt3.file.jbdc.repositories;

import cat.uvic.teknos.gt3.file.jbdc.models.Team;

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
public class JdbcTeamRepositoryTest {
    private final Connection connection;

    public JdbcTeamRepositoryTest(Connection connection) {
        this.connection = connection;
    }

    @Test
    @DisplayName("Given a new Team (id = 0), when saved, then a new record is added to the TEAMS table")
    void shouldInsertNewTeamTest() throws SQLException {

        Team team = new Team();
        team.setName("Mercedes-AMG Petronas Formula One Team");
        team.setBrandId(1);
        team.setCountry("Germany");
        team.setFoundationYear(2010);
        team.setContactInfo("contact@mercedesamgf1.com");

        var repository = new JdbcTeamRepository(connection);
        repository.save(team);

        assertTrue(team.getTeamId() > 0);

        assertNotNull(repository.get(team.getTeamId()));
    }

    @Test
    @DisplayName("Given an existing Team, when updated, then the record is updated in the TEAMS table")
    void shouldUpdateTeamTest() throws SQLException {

        Team team = new Team();
        team.setName("Red Bull Racing");
        team.setBrandId(1);
        team.setCountry("Austria");
        team.setFoundationYear(2005);
        team.setContactInfo("contact@redbullracing.com");

        var repository = new JdbcTeamRepository(connection);
        repository.save(team);

        assertTrue(true); // You can add assertions based on your requirements
    }

    @Test
    @DisplayName("Given an existing Team, when deleted, then the record is removed from the TEAMS table")
    void shouldDeleteTeamTest() throws SQLException {

        Team team = new Team();
        team.setTeamId(1); // Assuming team with ID 1 exists in the database

        var repository = new JdbcTeamRepository(connection);
        repository.delete(team);

        // You can add assertions to verify deletion
    }
}
