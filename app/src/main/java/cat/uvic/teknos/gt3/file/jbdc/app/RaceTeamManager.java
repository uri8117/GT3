package cat.uvic.teknos.gt3.file.jbdc.app;

import cat.uvic.teknos.gt3.domain.models.RaceTeam;
import cat.uvic.teknos.gt3.domain.repositories.RaceTeamRepository;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.Arrays;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;

import static cat.uvic.teknos.gt3.file.jbdc.app.IOUtils.readLine;

public class RaceTeamManager {

    /*private final PrintStream out;
    private final BufferedReader in;
    private final RaceTeamRepository raceTeamRepository;

    public RaceTeamManager(BufferedReader in, PrintStream out, RaceTeamRepository raceTeamRepository) {
        this.out = out;
        this.in = in;
        this.raceTeamRepository = raceTeamRepository;
    }

    public void start() {
        out.println("\n*** Race Team Management ***\n");

        var command = "";
        do {
            showRaceTeamMenu();
            command = readLine(in);

            switch (command) {
                case "1" -> insert();
                case "2" -> update();
                case "3" -> delete();
                case "4" -> getAll();
            }

        } while (!command.equals("exit"));

        out.println("\n*** Exiting Race Team Management ***\n");
    }

    private void getAll() {
        out.println("\n*** List of Race Teams ***\n");
        var raceTeams = raceTeamRepository.getAll();
        out.println(AsciiTable.getTable(raceTeams, Arrays.asList(
                new Column().header("Race Id").with(raceTeam -> String.valueOf(raceTeam.getRaceId())),
                new Column().header("Team Id").with(raceTeam -> String.valueOf(raceTeam.getTeamId()))
        )));
    }

    private void delete() {
        out.println("\n*** Delete Race Team ***\n");

        out.println("Enter the Race ID:");
        int raceId = Integer.parseInt(readLine(in));

        out.println("Enter the Team ID:");
        int teamId = Integer.parseInt(readLine(in));

        var raceTeam = raceTeamRepository.get(raceId, teamId);
        if (raceTeam != null) {
            raceTeamRepository.delete(raceTeam);
            out.println("\nSuccessfully deleted.\n");
        } else {
            out.println("\nRace team not found.\n");
        }
    }

    private void update() {
        out.println("\n*** Update Race Team ***\n");

        out.println("Enter the Race ID:");
        int raceId = Integer.parseInt(readLine(in));

        out.println("Enter the Team ID:");
        int teamId = Integer.parseInt(readLine(in));

        var raceTeam = raceTeamRepository.get(raceId, teamId);
        if (raceTeam != null) {
            // Race Team doesn't typically require updates, as it's usually a junction table
            out.println("\nRace Team updates are not supported.\n");
        } else {
            out.println("\nRace team not found.\n");
        }
    }

    private void insert() {
        out.println("\n*** Insert Race Team ***\n");

        out.println("Enter the Race ID:");
        int raceId = Integer.parseInt(readLine(in));

        out.println("Enter the Team ID:");
        int teamId = Integer.parseInt(readLine(in));

        var raceTeam = new RaceTeam();
        raceTeam.setRaceId(raceId);
        raceTeam.setTeamId(teamId);

        raceTeamRepository.save(raceTeam);
        out.println("\nSuccessfully inserted.\n");
    }

    private void showRaceTeamMenu() {
        out.println("\n*** Race Team Management Menu ***\n");
        out.println("1. Insert Race Team");
        out.println("2. Update Race Team");
        out.println("3. Delete Race Team");
        out.println("4. Get All Race Teams");
        out.println("Type 'exit' to quit.");
        out.println();
    }*/
}
