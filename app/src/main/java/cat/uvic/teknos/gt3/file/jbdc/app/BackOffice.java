package cat.uvic.teknos.gt3.file.jbdc.app;

import java.io.*;
import java.sql.SQLException;

import cat.uvic.teknos.gt3.domain.models.ModelFactory;
import cat.uvic.teknos.gt3.domain.repositories.RepositoryFactory;
import static cat.uvic.teknos.gt3.file.jbdc.app.IOUtils.*;

public class BackOffice {

    private final BufferedReader in;
    private final PrintStream out;
    private final RepositoryFactory repositoryFactory;
    private final ModelFactory modelFactory;

    public BackOffice(InputStream inputStream, OutputStream outputStream, RepositoryFactory repositoryFactory, ModelFactory modelFactory) {
        this.in = new BufferedReader(new InputStreamReader(inputStream));
        this.out = new PrintStream(outputStream);
        this.repositoryFactory = repositoryFactory;
        this.modelFactory = modelFactory;
    }

    public void start() throws SQLException {
        showWelcomeMessage();

        var command = "";
        do {
            showMainMenu();
            command = readLine(in);

            switch (command) {
                case "1" -> managerTeam();
                case "2" -> managerCircuit();
                case "3" -> managerDriver();
                case "4" -> managerCar();
                case "5" -> managerBrand();
                case "7" -> managerRace();
                case "8" -> managerRaceTeam();
                case "9" -> managerRaceDriver();
                case "10" -> managerRaceResult();
            }

        } while (!command.equals("exit"));

        out.println("\n*** Program Finished ***\n");
    }

    private void managerTeam() throws SQLException {
        new TeamManager(in, out, repositoryFactory.getTeamRepository(), modelFactory).start();
    }

    private void managerCircuit() throws SQLException {
        new CircuitManager(in, out, repositoryFactory.getCircuitRepository(), modelFactory).start();
    }

    private void managerDriver() throws SQLException {
        new DriverManager(in, out, repositoryFactory.getDriverRepository(), modelFactory).start();
    }

    private void managerCar() throws SQLException {
        new CarManager(in, out, repositoryFactory.getCarRepository(), modelFactory).start();
    }

    private void managerBrand() throws SQLException {
        new BrandManager(in, out, repositoryFactory.getBrandRepository(), modelFactory).start();
    }

    private void managerRace() throws SQLException {
        new RaceManager(in, out, repositoryFactory.getRaceRepository(), modelFactory).start();
    }

    private void managerRaceTeam() throws SQLException {
        //new RaceTeamManager(in, out, repositoryFactory.getRaceTeamRepository(), modelFactory).start();
    }

    private void managerRaceDriver() throws SQLException {
        //new RaceDriverManager(in, out, repositoryFactory.getRaceDriverRepository(), modelFactory).start();
    }

    private void managerRaceResult() throws SQLException {
        //new RaceResultManager(in, out, repositoryFactory.getRaceResultRepository(), modelFactory).start();
    }


    private void showWelcomeMessage() {
        out.println("\n*** Welcome to the GT3 Back Office ***\n");
        out.println("Select a menu option:");
        out.println();
    }

    private void showMainMenu() {
        out.println("\n*** Main Menu ***\n");
        out.println("1. Teams");
        out.println("2. Circuits");
        out.println("3. Drivers");
        out.println("4. Cars");
        out.println("5. Brands");
        out.println("7. Races");
        out.println("8. Race Teams");
        out.println("9. Race Drivers");
        out.println("10. Race Results");
        out.println("\nType 'exit' to quit.");
    }
}
