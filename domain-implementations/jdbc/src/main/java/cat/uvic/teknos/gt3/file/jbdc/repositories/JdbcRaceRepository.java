package cat.uvic.teknos.gt3.file.jbdc.repositories;

import cat.uvic.teknos.gt3.domain.models.Race;
import cat.uvic.teknos.gt3.domain.models.Driver;
import cat.uvic.teknos.gt3.domain.repositories.RaceRepository;
import cat.uvic.teknos.gt3.domain.repositories.CircuitRepository;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class JdbcRaceRepository implements RaceRepository {
    private static final String INSERT_RACE = "INSERT INTO RACE (ID_CIRCUIT, RACE_NAME, RACE_DATE) VALUES (?,?,?)";
    private static final String INSERT_RACE_DRIVER = "INSERT INTO RACE_DRIVER (ID_RACE, ID_DRIVER, POSITION) VALUES (?,?,?)";
    private static final String SELECT_ALL_RACES = "SELECT * FROM RACE";

    private final Connection connection;

    public JdbcRaceRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Race model) {
        if (model.getId() <= 0) {
            insert(model);
        } else {
            update(model);
        }
    }

    private void insert(Race model) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RACE, Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            preparedStatement.setInt(1, model.getCircuit().getId());
            preparedStatement.setString(2, model.getRaceName());
            preparedStatement.setDate(3, model.getRaceDate());
            preparedStatement.executeUpdate();

            ResultSet keys = preparedStatement.getGeneratedKeys();
            if (keys.next()) {
                model.setId(keys.getInt(1));
            } else {
                throw new SQLException("Creating race failed, no ID obtained.");
            }

//            if (model.getDrivers() != null) {
//                for (Driver driver : model.getDrivers()) {
//                    InsertRaceDriver(driver, model.getId(), position);
//                }
//            }

            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void InsertRaceDriver(Driver model, int raceId, int position) {
        try (
                var preparedStatement = connection.prepareStatement(INSERT_RACE_DRIVER)
        ) {
            preparedStatement.setInt(1, raceId);
            preparedStatement.setInt(2, model.getId());
            preparedStatement.setInt(3, position);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Método para actualizar una carrera en la base de datos.
    private void update(Race model) {
        // Implementa la lógica para actualizar una carrera existente en la base de datos
    }

    @Override
    public void delete(Race model) {

    }

    @Override
    public Race get(Integer id) {
        return null;
    }

    @Override
    public Set<Race> getAll() {
        return Set.of();
    }
}
