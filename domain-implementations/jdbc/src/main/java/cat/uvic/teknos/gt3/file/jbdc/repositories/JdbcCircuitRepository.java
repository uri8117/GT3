package cat.uvic.teknos.gt3.file.jbdc.repositories;

import cat.uvic.teknos.gt3.domain.models.Circuit;
import cat.uvic.teknos.gt3.domain.repositories.CircuitRepository;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class JdbcCircuitRepository implements CircuitRepository {
    private final Connection connection;

    public JdbcCircuitRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Circuit model) {
        if (model.getCircuitId() <= 0) {
            insert(model);
        } else {
            update(model);
        }
    }

    private void insert(Circuit model) {
        String sql = "INSERT INTO CIRCUITS (NAME, COUNTRY, LENGTH, TYPE) VALUES (?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, model.getName());
            statement.setString(2, model.getCountry());
            statement.setFloat(3, model.getLength());
            statement.setString(4, model.getType());

            statement.executeUpdate();
            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    model.setCircuitId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting circuit", e);
        }
    }

    private void update(Circuit model) {
        String sql = "UPDATE CIRCUITS SET NAME=?, COUNTRY=?, LENGTH=?, TYPE=? WHERE CIRCUIT_ID=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, model.getName());
            statement.setString(2, model.getCountry());
            statement.setFloat(3, model.getLength());
            statement.setString(4, model.getType());
            statement.setInt(5, model.getCircuitId());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No circuit to update");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating circuit", e);
        }
    }

    @Override
    public void delete(Circuit model) {
        String sql = "DELETE FROM CIRCUITS WHERE CIRCUIT_ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, model.getCircuitId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No circuit to delete");
            } else {
                System.out.println("Circuit deleted successfully");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting circuit", e);
        }
    }

    @Override
    public Circuit get(Integer id) {
        String sql = "SELECT * FROM CIRCUITS WHERE CIRCUIT_ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Circuit circuit = new cat.uvic.teknos.gt3.file.jbdc.models.Circuit();
                    circuit.setCircuitId(resultSet.getInt("CIRCUIT_ID"));
                    circuit.setName(resultSet.getString("NAME"));
                    circuit.setCountry(resultSet.getString("COUNTRY"));
                    circuit.setLength(resultSet.getFloat("LENGTH"));
                    circuit.setType(resultSet.getString("TYPE"));
                    return circuit;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving circuit", e);
        }
    }

    @Override
    public Set<Circuit> getAll() {
        String sql = "SELECT * FROM CIRCUITS";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            Set<Circuit> circuits = new HashSet<>();
            while (resultSet.next()) {
                Circuit circuit = new cat.uvic.teknos.gt3.file.jbdc.models.Circuit();
                circuit.setCircuitId(resultSet.getInt("CIRCUIT_ID"));
                circuit.setName(resultSet.getString("NAME"));
                circuit.setCountry(resultSet.getString("COUNTRY"));
                circuit.setLength(resultSet.getFloat("LENGTH"));
                circuit.setType(resultSet.getString("TYPE"));
                circuits.add(circuit);
            }
            return circuits;
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving circuits", e);
        }
    }
}
