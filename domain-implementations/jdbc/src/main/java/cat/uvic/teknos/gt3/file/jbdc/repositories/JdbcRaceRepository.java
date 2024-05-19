package cat.uvic.teknos.gt3.file.jbdc.repositories;

import cat.uvic.teknos.gt3.domain.models.Race;
import cat.uvic.teknos.gt3.domain.repositories.RaceRepository;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class JdbcRaceRepository implements RaceRepository {
    private final Connection connection;

    public JdbcRaceRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Race model) {
        if (model.getRaceId() <= 0) {
            insert(model);
        } else {
            update(model);
        }
    }

    private void insert(Race model) {
        String sql = "INSERT INTO RACES (NAME, CIRCUIT_ID, DATE) VALUES (?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, model.getName());
            statement.setInt(2, model.getCircuitId());
            statement.setDate(3, model.getDate());

            statement.executeUpdate();
            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    model.setRaceId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting race", e);
        }
    }

    private void update(Race model) {
        String sql = "UPDATE RACES SET NAME=?, CIRCUIT_ID=?, DATE=? WHERE RACE_ID=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, model.getName());
            statement.setInt(2, model.getCircuitId());
            statement.setDate(3, model.getDate());
            statement.setInt(4, model.getRaceId());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No race to update");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating race", e);
        }
    }

    @Override
    public void delete(Race model) {
        String sql = "DELETE FROM RACES WHERE RACE_ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, model.getRaceId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No race to delete");
            } else {
                System.out.println("Race deleted successfully");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting race", e);
        }
    }

    @Override
    public Race get(Integer id) {
        String sql = "SELECT * FROM RACES WHERE RACE_ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Race race = new cat.uvic.teknos.gt3.file.jbdc.models.Race();
                    race.setRaceId(resultSet.getInt("RACE_ID"));
                    race.setName(resultSet.getString("NAME"));
                    race.setCircuitId(resultSet.getInt("CIRCUIT_ID"));
                    race.setDate(resultSet.getDate("DATE"));
                    return race;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving race", e);
        }
    }

    @Override
    public Set<Race> getAll() {
        String sql = "SELECT * FROM RACES";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            Set<Race> races = new HashSet<>();
            while (resultSet.next()) {
                Race race = new cat.uvic.teknos.gt3.file.jbdc.models.Race();
                race.setRaceId(resultSet.getInt("RACE_ID"));
                race.setName(resultSet.getString("NAME"));
                race.setCircuitId(resultSet.getInt("CIRCUIT_ID"));
                race.setDate(resultSet.getDate("DATE"));
                races.add(race);
            }
            return races;
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving races", e);
        }
    }
}
