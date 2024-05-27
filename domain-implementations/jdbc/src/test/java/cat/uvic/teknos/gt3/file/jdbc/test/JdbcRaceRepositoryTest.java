package cat.uvic.teknos.gt3.file.jdbc.test;

import cat.uvic.teknos.gt3.file.jbdc.models.Driver;
import cat.uvic.teknos.gt3.file.jbdc.models.Race;
import cat.uvic.teknos.gt3.file.jbdc.models.Circuit;
import cat.uvic.teknos.gt3.file.jbdc.repositories.JdbcRaceRepository;

import com.fcardara.dbtestutils.junit.CreateSchemaExtension;
import com.fcardara.dbtestutils.junit.DbAssertions;
import com.fcardara.dbtestutils.junit.GetConnectionExtension;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.sql.Connection;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    public void shouldInsertRace() {
        // Get the existing circuit (Spa-Francorchamps)
        Circuit spaCircuit = new Circuit();
        spaCircuit.setId(4); // Assuming ID 4 corresponds to Spa-Francorchamps, update if needed

        // Create drivers with IDs 1, 3, and 5
        Driver driver1 = createDriverWithId(1);
        Driver driver3 = createDriverWithId(3);
        Driver driver5 = createDriverWithId(5);

        // Create a new race
        Race race = new Race();
        race.setCircuit(spaCircuit);
        race.setRaceName("Example Race");
        race.setRaceDate(Date.valueOf("2024-05-26"));

        // Add drivers to the race with positions
        var drivers = new HashSet<cat.uvic.teknos.gt3.domain.models.Driver>();
        drivers.add(driver1);
        drivers.add(driver3);
        drivers.add(driver5);
        race.setDrivers(drivers);

        // Save the race in the database
        JdbcRaceRepository raceRepository = new JdbcRaceRepository(connection);
        raceRepository.save(race);

        // Verify that an ID has been assigned to the race
        assertTrue(race.getId() > 0);

        // Verify that the race has been saved correctly
//        Race savedRace = raceRepository.get(race.getId());
//        assertNotNull(savedRace);

//        // Verify race attributes
//        assertEquals(race.getRaceName(), savedRace.getRaceName());
//        assertEquals(race.getRaceDate(), savedRace.getRaceDate());
//        assertEquals(race.getCircuit().getId(), savedRace.getCircuit().getId());

//        // Verify drivers are associated with the race
//        Set<cat.uvic.teknos.gt3.domain.models.Driver> savedDrivers = savedRace.getDrivers();
//        assertEquals(drivers.size(), savedDrivers.size());
//        assertTrue(savedDrivers.contains(driver1));
//        assertTrue(savedDrivers.contains(driver3));
//        assertTrue(savedDrivers.contains(driver5));

        // Verify that the positions of the drivers are correctly stored in the database
        //assertDriverPositionInDatabase(savedRace.getId(), driver1, expectedPositionForDriver1);
        //assertDriverPositionInDatabase(savedRace.getId(), driver3, expectedPositionForDriver3);
        //assertDriverPositionInDatabase(savedRace.getId(), driver5, expectedPositionForDriver5);

        DbAssertions.assertThat(connection)
                .table("RACE")
                .where("ID_RACE", race.getId())
                .hasOneLine();

    }

    private Driver createDriverWithId(int id) {
        Driver driver = new Driver();
        driver.setId(id);
        return driver;
    }
}
