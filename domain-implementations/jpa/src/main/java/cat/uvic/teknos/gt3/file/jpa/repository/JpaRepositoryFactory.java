package cat.uvic.teknos.gt3.file.jpa.repository;

import cat.uvic.teknos.gt3.domain.repositories.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class JpaRepositoryFactory implements RepositoryFactory {

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public JpaRepositoryFactory() throws IOException {
        var properties = new Properties();
        properties.load(JpaRepositoryFactory.class.getResourceAsStream("/persistence.properties"));
        entityManagerFactory = Persistence.createEntityManagerFactory("gt3_rep_mysql", properties);
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public BrandRepository getBrandRepository() throws SQLException {
        return new JpaBrandRepository(entityManager);
    }

    @Override
    public BrandDataRepository getBrandDataRepository() throws SQLException {
        return new JpaBrandDataRepository(entityManager);
    }

    @Override
    public CarRepository getCarRepository() throws SQLException {
        return new JpaCarRepository(entityManager);
    }

    @Override
    public CarDataRepository getCarDataRepository() throws SQLException {
        return new JpaCarDataRepository(entityManager);
    }

    @Override
    public CircuitRepository getCircuitRepository() throws SQLException {
        return new JpaCircuitRepository(entityManager);
    }

    @Override
    public DriverRepository getDriverRepository() throws SQLException {
        return new JpaDriverRepository(entityManager);
    }

    @Override
    public RaceRepository getRaceRepository() throws SQLException {
        return new JpaRaceRepository(entityManager);
    }
}


