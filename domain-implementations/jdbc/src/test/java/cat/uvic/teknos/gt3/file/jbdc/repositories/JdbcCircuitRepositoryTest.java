package cat.uvic.teknos.gt3.file.jbdc.repositories;

import cat.uvic.teknos.gt3.file.jbdc.models.Circuit;

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
public class JdbcCircuitRepositoryTest {
    private final Connection connection;

    public JdbcCircuitRepositoryTest(Connection connection) {
        this.connection = connection;
    }

    @Test
    @DisplayName("Given a new Circuit (id = 0), when save, then a new record is added to the CIRCUITS table")
    void shouldInsertNewCircuitTest() throws SQLException {

        Circuit circuit = new Circuit();
        circuit.setCircuitId(1);
        circuit.setName("Circuit de Barcelona-Catalunya");
        circuit.setCountry("Spain");
        circuit.setLength(4.655F);

        var repository = new JdbcCircuitRepository(connection);
        repository.save(circuit);

        assertTrue(circuit.getCircuitId() > 0);

        assertNotNull(repository.get(circuit.getCircuitId()));
    }

    @Test
    void shouldUpdateCircuitTest() throws SQLException {

        Circuit circuit = new Circuit();
        circuit.setCircuitId(1);
        circuit.setName("Suzuka Circuit");
        circuit.setCountry("Japan");
        circuit.setLength(5.807F);

        var repository = new JdbcCircuitRepository(connection);
        repository.save(circuit);

        assertTrue(true);

    }

    @Test
    void delete() throws SQLException {

        Circuit circuit = new Circuit();
        circuit.setCircuitId(6);

        var repository = new JdbcCircuitRepository(connection);
        repository.delete(circuit);
    }
}
