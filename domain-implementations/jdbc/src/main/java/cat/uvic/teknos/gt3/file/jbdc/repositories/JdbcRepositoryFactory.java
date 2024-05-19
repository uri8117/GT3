package cat.uvic.teknos.gt3.file.jbdc.repositories;

import cat.uvic.teknos.gt3.domain.repositories.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcRepositoryFactory implements RepositoryFactory {

    private Connection connection;

    public JdbcRepositoryFactory(){
        try {
            var properties = new Properties();
            properties.load(this.getClass().getResourceAsStream("/datasource.properties"));
            connection = DriverManager.getConnection(String.format("%s:%s://%s/%s",
                    properties.getProperty("protocol"),
                    properties.getProperty("subprotocol"),
                    properties.getProperty("url"),
                    properties.getProperty("database")), properties.getProperty("user"), properties.getProperty("password"));
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public TeamRepository getTeamRepository() throws SQLException {
        return new JdbcTeamRepository(connection);
    }

    @Override
    public CircuitRepository getCircuitRepository() throws SQLException {
        return new JdbcCircuitRepository(connection);
    }

    @Override
    public DriverRepository getDriverRepository() throws SQLException {
        return new JdbcDriverRepository(connection);
    }

    @Override
    public CarRepository getCarRepository() throws SQLException {
        return new JdbcCarRepository(connection);
    }

    public BrandRepository getBrandRepository() throws SQLException {
        return new JdbcBrandRepository(connection);
    }

    @Override
    public CarModelRepository getCarModelRepository() throws SQLException {
        return new JdbcCarModelRepository(connection);
    }

    @Override
    public RaceRepository getRaceRepository() throws SQLException {
        return new JdbcRaceRepository(connection);
    }

    @Override
    public RaceTeamRepository getRaceTeamRepository() throws SQLException {
        return null;
    }

    @Override
    public RaceDriverRepository getRaceDriverRepository() throws SQLException {
        return null;
    }

    @Override
    public RaceResultsRepository getRaceResultRepository() throws SQLException {
        return null;
    }


}