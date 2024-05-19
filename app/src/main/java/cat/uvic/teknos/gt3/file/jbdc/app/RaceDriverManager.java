package cat.uvic.teknos.gt3.file.jbdc.app;

import cat.uvic.teknos.gt3.domain.models.RaceDriver;
import cat.uvic.teknos.gt3.domain.repositories.RaceDriverRepository;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.Arrays;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;

import static cat.uvic.teknos.gt3.file.jbdc.app.IOUtils.readLine;

public class RaceDriverManager {

    /*private final PrintStream out;
    private final BufferedReader in;
    private final RaceDriverRepository raceDriverRepository;

    public RaceDriverManager(BufferedReader in, PrintStream out, RaceDriverRepository raceDriverRepository) {
        this.out = out;
        this.in = in;
        this.raceDriverRepository = raceDriverRepository;
    }

    public void start() {
        out.println("\n*** Race Driver Management ***\n");

        var command = "";
        do {
            showRaceDriverMenu();
            command = readLine(in);

            switch (command) {
                case "1" -> insert();
                case "2" -> update();
                case "3" -> delete();
                case "4" -> getAll();
            }

        } while (!command.equals("exit"));

        out.println("\n*** Exiting Race Driver Management ***\n");
    }

    private void getAll() {
        out.println("\n*** List of Race Drivers ***\n");
        var raceDrivers = raceDriverRepository.getAll();
        out.println(AsciiTable.getTable(raceDrivers, Arrays.asList(
                new Column().header("Race Id").with(raceDriver -> String.valueOf(raceDriver.getRaceId())),
                new Column().header("Driver Id").with(raceDriver -> String.valueOf(raceDriver.getDriverId()))
        )));
    }

    private void delete() {
        out.println("\n*** Delete Race Driver ***\n");

        out.println("Enter the Race ID:");
        int raceId = Integer.parseInt(readLine(in));

        out.println("Enter the Driver ID:");
        int driverId = Integer.parseInt(readLine(in));

        var raceDriver = raceDriverRepository.get(raceId, driverId);
        if (raceDriver != null) {
            raceDriverRepository.delete(raceDriver);
            out.println("\nSuccessfully deleted.\n");
        } else {
            out.println("\nRace driver not found.\n");
        }
    }

    private void update() {
        out.println("\n*** Update Race Driver ***\n");

        out.println("Enter the Race ID:");
        int raceId = Integer.parseInt(readLine(in));

        out.println("Enter the Driver ID:");
        int driverId = Integer.parseInt(readLine(in));

        var raceDriver = raceDriverRepository.get(raceId, driverId);
        if (raceDriver != null) {
            // Race Driver doesn't typically require updates, as it's usually a junction table
            out.println("\nRace Driver updates are not supported.\n");
        } else {
            out.println("\nRace driver not found.\n");
        }
    }

    private void insert() {
        out.println("\n*** Insert Race Driver ***\n");

        out.println("Enter the Race ID:");
        int raceId = Integer.parseInt(readLine(in));

        out.println("Enter the Driver ID:");
        int driverId = Integer.parseInt(readLine(in));

        var raceDriver = new RaceDriver();
        raceDriver.setRaceId(raceId);
        raceDriver.setDriverId(driverId);

        raceDriverRepository.save(raceDriver);
        out.println("\nSuccessfully inserted.\n");
    }

    private void showRaceDriverMenu() {
        out.println("\n*** Race Driver Management Menu ***\n");
        out.println("1. Insert Race Driver");
        out.println("2. Update Race Driver");
        out.println("3. Delete Race Driver");
        out.println("4. Get All Race Drivers");
        out.println("Type 'exit' to quit.");
        out.println();
    }*/
}
