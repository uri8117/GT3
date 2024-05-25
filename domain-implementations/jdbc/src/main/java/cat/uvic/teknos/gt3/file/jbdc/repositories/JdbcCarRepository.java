package cat.uvic.teknos.gt3.file.jbdc.repositories;

import cat.uvic.teknos.gt3.domain.models.Car;
import cat.uvic.teknos.gt3.domain.repositories.CarRepository;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class JdbcCarRepository implements CarRepository {
    private final Connection connection;

    public JdbcCarRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Car model) {
        if (model.getCarId() <= 0) {
            insert(model);
        } else {
            update(model);
        }
    }

    private void insert(Car model) {
        String sql = "INSERT INTO CARS (MODEL, MANUFACTURING_YEAR, POWER, WEIGHT, ENGINE_TYPE, CHASSIS_MANUFACTURER, TEAM_ID) VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, model.getModel());
            statement.setInt(2, model.getManufacturingYear());
            statement.setInt(3, model.getPower());
            statement.setInt(4, model.getWeight());
            statement.setString(5, model.getEngineType());
            statement.setString(6, model.getChassisManufacturer());
            statement.setInt(7, model.getTeamId());

            statement.executeUpdate();
            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    model.setCarId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting car", e);
        }
    }

    private void update(Car model) {
        String sql = "UPDATE CARS SET MODEL=?, MANUFACTURING_YEAR=?, POWER=?, WEIGHT=?, ENGINE_TYPE=?, CHASSIS_MANUFACTURER=?, TEAM_ID=? WHERE CAR_ID=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, model.getModel());
            statement.setInt(2, model.getManufacturingYear());
            statement.setInt(3, model.getPower());
            statement.setInt(4, model.getWeight());
            statement.setString(5, model.getEngineType());
            statement.setString(6, model.getChassisManufacturer());
            statement.setInt(7, model.getTeamId());
            statement.setInt(8, model.getCarId());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No car to update");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating car", e);
        }
    }

    @Override
    public void delete(Car model) {
        String sql = "DELETE FROM CARS WHERE CAR_ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, model.getCarId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No car to delete");
            } else {
                System.out.println("Car deleted successfully");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting car", e);
        }
    }

    @Override
    public Car get(Integer id) {
        String sql = "SELECT * FROM CARS WHERE CAR_ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Car car = new cat.uvic.teknos.gt3.file.jbdc.models.Car();
                    car.setCarId(resultSet.getInt("CAR_ID"));
                    car.setModel(resultSet.getString("MODEL"));
                    car.setManufacturingYear(resultSet.getInt("MANUFACTURING_YEAR"));
                    car.setPower(resultSet.getInt("POWER"));
                    car.setWeight(resultSet.getInt("WEIGHT"));
                    car.setEngineType(resultSet.getString("ENGINE_TYPE"));
                    car.setChassisManufacturer(resultSet.getString("CHASSIS_MANUFACTURER"));
                    car.setTeamId(resultSet.getInt("TEAM_ID"));
                    return car;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving car", e);
        }
    }

    @Override
    public Set<Car> getAll() {
        String sql = "SELECT * FROM CARS";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            Set<Car> cars = new HashSet<>();
            while (resultSet.next()) {
                Car car = new cat.uvic.teknos.gt3.file.jbdc.models.Car();
                car.setCarId(resultSet.getInt("CAR_ID"));
                car.setModel(resultSet.getString("MODEL"));
                car.setManufacturingYear(resultSet.getInt("MANUFACTURING_YEAR"));
                car.setPower(resultSet.getInt("POWER"));
                car.setWeight(resultSet.getInt("WEIGHT"));
                car.setEngineType(resultSet.getString("ENGINE_TYPE"));
                car.setChassisManufacturer(resultSet.getString("CHASSIS_MANUFACTURER"));
                car.setTeamId(resultSet.getInt("TEAM_ID"));
                cars.add(car);
            }
            return cars;
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving cars", e);
        }
    }
}
