package cat.uvic.teknos.gt3.file.jbdc.repositories;

import cat.uvic.teknos.gt3.domain.models.CarData;
import cat.uvic.teknos.gt3.domain.models.Driver;
import cat.uvic.teknos.gt3.domain.repositories.CarRepository;
import cat.uvic.teknos.gt3.domain.models.Car;
import cat.uvic.teknos.gt3.domain.repositories.CarRepository;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;


public class JdbcCarRepository implements CarRepository {

    private static final String INSERT_CAR = "INSERT INTO CAR (ID_BRAND, MODEL_NAME) VALUES (?, ?)";
    private static final String INSERT_CAR_DRIVER = "INSERT INTO CAR_DRIVER (ID_CAR, ID_DRIVER) VALUES (?, ?)";
    private static final String INSERT_CAR_DATA = "INSERT INTO CAR_DATA (ID_CAR, HORSEPOWER, WEIGHT) VALUES (?, ?)";

    private final Connection connection;

    public JdbcCarRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Car model) {
        if (model.getId() <= 0) {
            insert(model);
        } else {
            update(model);
        }
    }

    private void insert(Car model) {
        try (var preparedStatement = connection.prepareStatement(INSERT_CAR, Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            preparedStatement.setInt(1, model.getBrand().getId());
            preparedStatement.setString(2, model.getModelName());
            preparedStatement.executeUpdate();

            var keys = preparedStatement.getGeneratedKeys();
            if (keys.next()) {
                model.setId(keys.getInt(1));
                createDataForCar(model.getId(), model.getCarData());
            } else {
                throw new SQLException("Creating car failed, no ID obtained.");
            }

            if (model.getDrivers() != null) {
                for (Driver driver : model.getDrivers()) {
                    insertCarDriver(model.getId(), driver);
                }
            }

            connection.commit();
        } catch (SQLException e) {
            rollback();
            throw new RuntimeException(e);
        } finally {
            setAutocommitTrue();
        }
    }

    private void insertCarDriver(int carId, Driver model) {
        try (var preparedStatement = connection.prepareStatement(INSERT_CAR_DRIVER)) {
            preparedStatement.setInt(1, carId);
            preparedStatement.setInt(2, model.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void createDataForCar(int carId, CarData model) {
        try (var preparedStatement = connection.prepareStatement(INSERT_CAR_DATA)) {
            preparedStatement.setInt(1, carId);
            preparedStatement.setInt(2, model.getHorsePower());
            preparedStatement.setInt(3, model.getWeight());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void update(Car model) {
        try (var carStatement = connection.prepareStatement("UPDATE CAR SET ID_BRAND = ?, MODEL_NAME = ? WHERE ID_CAR = ?")) {
            connection.setAutoCommit(false);
            carStatement.setInt(1, model.getBrand().getId());
            carStatement.setString(2, model.getModelName());
            carStatement.setInt(3, model.getId());
            carStatement.executeUpdate();

            updateCarData(model.getCarData(), model.getId());

            if (model.getDrivers() != null) {
                deleteCarDrivers(model.getId());
                for (Driver driver : model.getDrivers()) {
                    insertCarDriver(model.getId(), driver);
                }
            }

            connection.commit();
        } catch (SQLException e) {
            rollback();
            throw new RuntimeException(e);
        } finally {
            setAutocommitTrue();
        }
    }

    private void updateCarData(CarData model, int carId) {
        try (var carDataStatement = connection.prepareStatement("UPDATE CAR_DATA SET HORSEPOWER = ?, WEIGHT = ? WHERE ID_CAR = ?")) {
            carDataStatement.setInt(1, model.getHorsePower());
            carDataStatement.setInt(2, model.getWeight());
            carDataStatement.setInt(3, carId);
            carDataStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteCarDrivers(int carId) {
        try (var preparedStatement = connection.prepareStatement("DELETE FROM CAR_DRIVER WHERE ID_CAR = ?")) {
            preparedStatement.setInt(1, carId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Car model) {
        try (var preparedStatement = connection.prepareStatement("DELETE FROM CAR WHERE ID_CAR = ?")) {
            connection.setAutoCommit(false);
            preparedStatement.setInt(1, model.getId());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            rollback();
            throw new RuntimeException(e);
        } finally {
            setAutocommitTrue();
        }
    }

    @Override
    public Car get(Integer id) {
        String query = "SELECT * FROM CAR WHERE ID_CAR = ?";
        String queryCarData = "SELECT * FROM CAR_DATA WHERE ID_CAR = ?";

        try (var statement = connection.prepareStatement(query);
             var carDataStatement = connection.prepareStatement(queryCarData)) {

            statement.setInt(1, id);
            carDataStatement.setInt(1, id);
            var resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Car result = new cat.uvic.teknos.gt3.file.jbdc.models.Car();
                result.setId(resultSet.getInt("ID_CAR"));
                result.setModelName(resultSet.getString("MODEL_NAME"));
                result.setBrandId(resultSet.getInt("ID_BRAND"));

                var resultSetData = carDataStatement.executeQuery();
                if (resultSetData.next()) {
                    CarData carData = new cat.uvic.teknos.gt3.file.jbdc.models.CarData();
                    carData.setHorsePower(resultSetData.getInt("HORSEPOWER"));
                    carData.setWeight(resultSetData.getInt("WEIGHT"));
                    result.setCarData(carData);
                }

                return result;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Set<Car> getAll() {
        try (var preparedStatement = connection.prepareStatement("SELECT * FROM CAR");
             var carDataStatement = connection.prepareStatement("SELECT * FROM CAR_DATA")) {

            var cars = new HashSet<Car>();
            var resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Car car = new cat.uvic.teknos.gt3.file.jbdc.models.Car();
                car.setId(resultSet.getInt("ID_CAR"));
                car.setModelName(resultSet.getString("MODEL_NAME"));
                car.setBrandId(resultSet.getInt("ID_BRAND"));

                carDataStatement.setInt(1, car.getId());
                var resultSetData = carDataStatement.executeQuery();
                if (resultSetData.next()) {
                    CarData carData = new cat.uvic.teknos.gt3.file.jbdc.models.CarData();
                    carData.setHorsePower(resultSetData.getInt("HORSEPOWER"));
                    carData.setWeight(resultSetData.getInt("WEIGHT"));
                    car.setCarData(carData);
                }

                cars.add(car);
            }

            return cars;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setAutocommitTrue() {
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void rollback() {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}