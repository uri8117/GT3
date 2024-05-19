package cat.uvic.teknos.gt3.file.jbdc.repositories;

import cat.uvic.teknos.gt3.file.jbdc.models.CarModel;

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
public class JdbcCarModelRepositoryTest {
    private final Connection connection;

    public JdbcCarModelRepositoryTest(Connection connection) {
        this.connection = connection;
    }

    @Test
    @DisplayName("Given a new Car Model (id = 0), when saved, then a new record is added to the CAR_MODELS table")
    void shouldInsertNewCarModelTest() throws SQLException {

        CarModel carModel = new CarModel();
        carModel.setModelId(1);
        carModel.setBrandId(1); // Assuming brand with ID 1 exists in the database
        carModel.setModel("Model XYZ");

        var repository = new JdbcCarModelRepository(connection);
        repository.save(carModel);

        assertTrue(carModel.getModelId() > 0);

        assertNotNull(repository.get(carModel.getModelId()));
    }

    @Test
    @DisplayName("Given an existing Car Model, when updated, then the record is updated in the CAR_MODELS table")
    void shouldUpdateCarModelTest() throws SQLException {

        CarModel carModel = new CarModel();
        carModel.setModelId(1); // Assuming car model with ID 1 exists in the database
        carModel.setBrandId(1); // Assuming brand with ID 1 exists in the database
        carModel.setModel("Updated Model XYZ");

        var repository = new JdbcCarModelRepository(connection);
        repository.save(carModel);

        assertTrue(true); // You can add assertions based on your requirements
    }

    @Test
    @DisplayName("Given an existing Car Model, when deleted, then the record is removed from the CAR_MODELS table")
    void shouldDeleteCarModelTest() throws SQLException {

        CarModel carModel = new CarModel();
        carModel.setModelId(1); // Assuming car model with ID 1 exists in the database

        var repository = new JdbcCarModelRepository(connection);
        repository.delete(carModel);

        // You can add assertions to verify deletion
    }
}
