package cat.uvic.teknos.gt3.file.jbdc.repositories;

import cat.uvic.teknos.gt3.domain.models.CarModel;
import cat.uvic.teknos.gt3.domain.repositories.CarModelRepository;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class JdbcCarModelRepository implements CarModelRepository {
    private final Connection connection;

    public JdbcCarModelRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(CarModel model) {
        if (model.getModelId() <= 0) {
            insert(model);
        } else {
            update(model);
        }
    }

    private void insert(CarModel model) {
        String sql = "INSERT INTO CAR_MODELS (BRAND_ID, MODEL) VALUES (?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, model.getBrandId());
            statement.setString(2, model.getModel());

            statement.executeUpdate();
            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    model.setModelId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting car model", e);
        }
    }

    private void update(CarModel model) {
        String sql = "UPDATE CAR_MODELS SET BRAND_ID=?, MODEL=? WHERE MODEL_ID=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, model.getBrandId());
            statement.setString(2, model.getModel());
            statement.setInt(3, model.getModelId());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No car model to update");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating car model", e);
        }
    }

    @Override
    public void delete(CarModel model) {
        String sql = "DELETE FROM CAR_MODELS WHERE MODEL_ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, model.getModelId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No car model to delete");
            } else {
                System.out.println("Car model deleted successfully");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting car model", e);
        }
    }

    @Override
    public CarModel get(Integer id) {
        String sql = "SELECT * FROM CAR_MODELS WHERE MODEL_ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    CarModel carModel = new cat.uvic.teknos.gt3.file.jbdc.models.CarModel();
                    carModel.setModelId(resultSet.getInt("MODEL_ID"));
                    carModel.setBrandId(resultSet.getInt("BRAND_ID"));
                    carModel.setModel(resultSet.getString("MODEL"));
                    return carModel;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving car model", e);
        }
    }

    @Override
    public Set<CarModel> getAll() {
        String sql = "SELECT * FROM CAR_MODELS";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            Set<CarModel> carModels = new HashSet<>();
            while (resultSet.next()) {
                CarModel carModel = new cat.uvic.teknos.gt3.file.jbdc.models.CarModel();
                carModel.setModelId(resultSet.getInt("MODEL_ID"));
                carModel.setBrandId(resultSet.getInt("BRAND_ID"));
                carModel.setModel(resultSet.getString("MODEL"));
                carModels.add(carModel);
            }
            return carModels;
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving car models", e);
        }
    }
}
