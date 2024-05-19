package cat.uvic.teknos.gt3.file.jbdc.app;

import cat.uvic.teknos.gt3.domain.models.ModelFactory;
import cat.uvic.teknos.gt3.domain.models.Driver;
import cat.uvic.teknos.gt3.domain.repositories.DriverRepository;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.Arrays;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;

import static cat.uvic.teknos.gt3.file.jbdc.app.IOUtils.readLine;

public class DriverManager {

    private final PrintStream out;
    private final BufferedReader in;
    private final DriverRepository driverRepository;
    private final ModelFactory modelFactory;

    public DriverManager(BufferedReader in, PrintStream out, DriverRepository driverRepository, ModelFactory modelFactory) {
        this.out = out;
        this.in = in;
        this.driverRepository = driverRepository;
        this.modelFactory = modelFactory;
    }

    public void start() {
        out.println("\n*** Driver Management ***\n");

        var command = "";
        do {
            showDriverMenu();
            command = readLine(in);

            switch (command) {
                case "1" -> insert();
                case "2" -> update();
                case "3" -> delete();
                case "4" -> getAll();
            }

        } while (!command.equals("exit"));

        out.println("\n*** Exiting Driver Management ***\n");
    }

    private void getAll() {
        out.println("\n*** List of Drivers ***\n");
        var drivers = driverRepository.getAll();
        out.println(AsciiTable.getTable(drivers, Arrays.asList(
                new Column().header("Id").with(driver -> String.valueOf(driver.getDriverId())),
                new Column().header("Name").with(Driver::getName),
                new Column().header("Nationality").with(Driver::getNationality),
                new Column().header("Age").with(driver -> String.valueOf(driver.getAge())),
                new Column().header("TeamId").with(driver -> String.valueOf(driver.getTeamId()))
        )));
    }

    private void delete() {
        out.println("\n*** Delete Driver ***\n");

        out.println("Enter the ID of the driver to delete:");
        int id = Integer.parseInt(readLine(in));

        var driver = driverRepository.get(id);
        if (driver != null) {
            driverRepository.delete(driver);
            out.println("\nSuccessfully deleted.\n");
        } else {
            out.println("\nDriver not found.\n");
        }
    }

    private void update() {
        out.println("\n*** Update Driver ***\n");

        out.println("Enter the ID of the driver to update:");
        int id = Integer.parseInt(readLine(in));

        var driver = driverRepository.get(id);
        if (driver != null) {
            out.println("Enter new Name:");
            driver.setName(readLine(in));

            out.println("Enter new Nationality:");
            driver.setNationality(readLine(in));

            out.println("Enter new Age:");
            driver.setAge(Integer.parseInt(readLine(in)));

            out.println("Enter new TeamId:");
            driver.setTeamId(Integer.parseInt(readLine(in)));

            driverRepository.save(driver);
            out.println("\nSuccessfully updated.\n");
        } else {
            out.println("\nDriver not found.\n");
        }
    }

    private void insert() {
        out.println("\n*** Insert Driver ***\n");

        var driver = modelFactory.createDriver();

        out.println("Enter the Name:");
        driver.setName(readLine(in));

        out.println("Enter the Nationality:");
        driver.setNationality(readLine(in));

        out.println("Enter the Age:");
        driver.setAge(Integer.parseInt(readLine(in)));

        out.println("Enter the TeamId:");
        driver.setTeamId(Integer.parseInt(readLine(in)));

        driverRepository.save(driver);
        out.println("\nSuccessfully inserted.\n");
    }

    private void showDriverMenu() {
        out.println("\n*** Driver Management Menu ***\n");
        out.println("1. Insert Driver");
        out.println("2. Update Driver");
        out.println("3. Delete Driver");
        out.println("4. Get All Drivers");
        out.println("Type 'exit' to quit.");
        out.println();
    }
}
