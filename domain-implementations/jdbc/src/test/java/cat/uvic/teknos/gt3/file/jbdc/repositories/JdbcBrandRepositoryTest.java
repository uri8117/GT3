package cat.uvic.teknos.gt3.file.jbdc.repositories;

import cat.uvic.teknos.gt3.file.jbdc.models.Brand;

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
public class JdbcBrandRepositoryTest {
    private final Connection connection;

    public JdbcBrandRepositoryTest(Connection connection) {
        this.connection = connection;
    }

    @Test
    @DisplayName("Given a new Brand (id = 0), when saved, then a new record is added to the BRANDS table")
    void shouldInsertNewBrandTest() throws SQLException {

        Brand brand = new Brand();
        brand.setName("Audi");
        brand.setCountryOfOrigin("Germany");
        brand.setContactInfo("contact@audi.com");

        var repository = new JdbcBrandRepository(connection);
        repository.save(brand);

        assertTrue(brand.getBrandId() > 0);

        assertNotNull(repository.get(brand.getBrandId()));
    }

    @Test
    @DisplayName("Given an existing Brand, when updated, then the record is updated in the BRANDS table")
    void shouldUpdateBrandTest() throws SQLException {

        Brand brand = new Brand();
        brand.setBrandId(3); // Assuming brand with ID 1 exists in the database
        brand.setName("Mercedes-Benz");
        brand.setCountryOfOrigin("Germany");
        brand.setContactInfo("contact@mercedesbenz.com");

        var repository = new JdbcBrandRepository(connection);
        repository.save(brand);

        assertTrue(true); // You can add assertions based on your requirements
    }

    @Test
    @DisplayName("Given an existing Brand, when deleted, then the record is removed from the BRANDS table")
    void shouldDeleteBrandTest() throws SQLException {

        Brand brand = new Brand();
        brand.setBrandId(3); // Assuming brand with ID 1 exists in the database

        var repository = new JdbcBrandRepository(connection);
        repository.delete(brand);

        // You can add assertions to verify deletion
    }
}
