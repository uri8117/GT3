package cat.uvic.teknos.gt3.file.jbdc.app;

import cat.uvic.teknos.gt3.domain.models.ModelFactory;
import cat.uvic.teknos.gt3.domain.repositories.RepositoryFactory;
import cat.uvic.teknos.gt3.file.jbdc.models.JdbcModelFactory;
import cat.uvic.teknos.gt3.file.jbdc.repositories.JdbcRepositoryFactory;

import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException {

        RepositoryFactory repositoryFactory = new JdbcRepositoryFactory();
        ModelFactory modelFactory = new JdbcModelFactory();

        var backOffice = new BackOffice(System.in, System.out, repositoryFactory, modelFactory);

        backOffice.start();
    }
}
