package cat.uvic.teknos.gt3.file.jbdc.app;

import cat.uvic.teknos.gt3.domain.models.ModelFactory;
import cat.uvic.teknos.gt3.domain.models.Race;
import cat.uvic.teknos.gt3.domain.repositories.RaceRepository;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.sql.Date;
import java.util.Arrays;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;

import static cat.uvic.teknos.gt3.file.jbdc.app.IOUtils.readLine;

public class RaceManager {

    private final PrintStream out;
    private final BufferedReader in;
    private final RaceRepository raceRepository;
    private final ModelFactory modelFactory;

    public RaceManager(BufferedReader in, PrintStream out, RaceRepository raceRepository, ModelFactory modelFactory) {
        this.out = out;
        this.in = in;
        this.raceRepository = raceRepository;
        this.modelFactory = modelFactory;
    }

    public void start() {
        out.println("\n*** Race Management ***\n");

        var command = "";
        do {
            showRaceMenu();
            command = readLine(in);

            switch (command) {
                case "1" -> insert();
                case "2" -> update();
                case "3" -> delete();
                case "4" -> getAll();
            }

        } while (!command.equals("exit"));

        out.println("\n*** Exiting Race Management ***\n");
    }

    private void getAll() {
        out.println("\n*** List of Races ***\n");
        var races = raceRepository.getAll();
        out.println(AsciiTable.getTable(races, Arrays.asList(
                new Column().header("Id").with(race -> String.valueOf(race.getRaceId())),
                new Column().header("Name").with(Race::getName),
                new Column().header("Circuit Id").with(race -> String.valueOf(race.getCircuitId())),
                new Column().header("Date").with(race -> String.valueOf(race.getDate()))
        )));
    }

    private void delete() {
        out.println("\n*** Delete Race ***\n");

        out.println("Enter the ID of the race to delete:");
        int id = Integer.parseInt(readLine(in));

        var race = raceRepository.get(id);
        if (race != null) {
            raceRepository.delete(race);
            out.println("\nSuccessfully deleted.\n");
        } else {
            out.println("\nRace not found.\n");
        }
    }

    private void update() {
        out.println("\n*** Update Race ***\n");

        out.println("Enter the ID of the race to update:");
        int id = Integer.parseInt(readLine(in));

        var race = raceRepository.get(id);
        if (race != null) {
            out.println("Enter new Name:");
            race.setName(readLine(in));

            out.println("Enter new Circuit Id:");
            race.setCircuitId(Integer.parseInt(readLine(in)));

            out.println("Enter new Date (yyyy-mm-dd):");
            race.setDate(Date.valueOf(readLine(in)));

            raceRepository.save(race);
            out.println("\nSuccessfully updated.\n");
        } else {
            out.println("\nRace not found.\n");
        }
    }

    private void insert() {
        out.println("\n*** Insert Race ***\n");

        var race = modelFactory.createRace();

        out.println("Enter the Name:");
        race.setName(readLine(in));

        out.println("Enter the Circuit Id:");
        race.setCircuitId(Integer.parseInt(readLine(in)));

        out.println("Enter the Date (yyyy-mm-dd):");
        race.setDate(Date.valueOf(readLine(in)));

        raceRepository.save(race);
        out.println("\nSuccessfully inserted.\n");
    }

    private void showRaceMenu() {
        out.println("\n*** Race Management Menu ***\n");
        out.println("1. Insert Race");
        out.println("2. Update Race");
        out.println("3. Delete Race");
        out.println("4. Get All Races");
        out.println("Type 'exit' to quit.");
        out.println();
    }
}
