package cat.uvic.teknos.gt3.domain.repositories;


import java.sql.SQLException;

public interface RepositoryFactory {
    TeamRepository getTeamRepository() throws SQLException;

    CircuitRepository getCircuitRepository() throws SQLException;

    DriverRepository getDriverRepository() throws SQLException;

    CarRepository getCarRepository() throws SQLException;

    BrandRepository getBrandRepository() throws SQLException;

    CarModelRepository getCarModelRepository() throws SQLException;

    RaceRepository getRaceRepository() throws SQLException;

    RaceTeamRepository getRaceTeamRepository() throws SQLException;

    RaceDriverRepository getRaceDriverRepository() throws SQLException;

    RaceResultsRepository getRaceResultRepository() throws SQLException;
}
