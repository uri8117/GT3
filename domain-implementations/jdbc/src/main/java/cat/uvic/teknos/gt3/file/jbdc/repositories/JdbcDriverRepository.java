package cat.uvic.teknos.gt3.file.jbdc.repositories;

import cat.uvic.teknos.gt3.domain.models.Driver;
import cat.uvic.teknos.gt3.domain.repositories.DriverRepository;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class JdbcDriverRepository implements DriverRepository {
    private final Connection connection;

    public JdbcDriverRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Driver model) {
        if (model.getDriverId() <= 0) {
            insert(model);
        } else {
            update(model);
        }
    }

    private void insert(Driver model) {
        String sql = "INSERT INTO DRIVERS (NAME, NATIONALITY, AGE, TEAM_ID) VALUES (?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, model.getName());
            statement.setString(2, model.getNationality());
            statement.setInt(3, model.getAge());
            statement.setInt(4, model.getTeamId());

            statement.executeUpdate();
            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    model.setDriverId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting driver", e);
        }
    }

    private void update(Driver model) {
        String sql = "UPDATE DRIVERS SET NAME=?, NATIONALITY=?, AGE=?, TEAM_ID=? WHERE DRIVER_ID=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, model.getName());
            statement.setString(2, model.getNationality());
            statement.setInt(3, model.getAge());
            statement.setInt(4, model.getTeamId());
            statement.setInt(5, model.getDriverId());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No driver to update");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating driver", e);
        }
    }

    @Override
    public void delete(Driver model) {
        String sql = "DELETE FROM DRIVERS WHERE DRIVER_ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, model.getDriverId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No driver to delete");
            } else {
                System.out.println("Driver deleted successfully");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting driver", e);
        }
    }

    @Override
    public Driver get(Integer id) {
        String sql = "SELECT * FROM DRIVERS WHERE DRIVER_ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Driver driver = new cat.uvic.teknos.gt3.file.jbdc.models.Driver();
                    driver.setDriverId(resultSet.getInt("DRIVER_ID"));
                    driver.setName(resultSet.getString("NAME"));
                    driver.setNationality(resultSet.getString("NATIONALITY"));
                    driver.setAge(resultSet.getInt("AGE"));
                    driver.setTeamId(resultSet.getInt("TEAM_ID"));
                    return driver;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving driver", e);
        }
    }

    @Override
    public Set<Driver> getAll() {
        String sql = "SELECT * FROM DRIVERS";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            Set<Driver> drivers = new HashSet<>();
            while (resultSet.next()) {
                Driver driver = new cat.uvic.teknos.gt3.file.jbdc.models.Driver();
                driver.setDriverId(resultSet.getInt("DRIVER_ID"));
                driver.setName(resultSet.getString("NAME"));
                driver.setNationality(resultSet.getString("NATIONALITY"));
                driver.setAge(resultSet.getInt("AGE"));
                driver.setTeamId(resultSet.getInt("TEAM_ID"));
                drivers.add(driver);
            }
            return drivers;
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving drivers", e);
        }
    }
}
