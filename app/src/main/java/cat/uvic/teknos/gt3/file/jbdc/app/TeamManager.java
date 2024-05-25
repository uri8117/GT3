package cat.uvic.teknos.gt3.file.jbdc.app;

import cat.uvic.teknos.gt3.domain.models.ModelFactory;
import cat.uvic.teknos.gt3.domain.repositories.TeamRepository;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.Arrays;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;

import static cat.uvic.teknos.gt3.file.jbdc.app.IOUtils.*;

public class TeamManager {

    private final PrintStream out;
    private final BufferedReader in;
    private final TeamRepository teamRepository;
    private final ModelFactory modelFactory;

    public TeamManager(BufferedReader in, PrintStream out, TeamRepository teamRepository, ModelFactory modelFactory) {
        this.out = out;
        this.in = in;
        this.teamRepository = teamRepository;
        this.modelFactory = modelFactory;
    }

    public void start() {
        out.println("\n*** Team Management ***\n");

        var command = "";
        do {
            showTeamMenu();
            command = readLine(in);

            switch (command) {
                case "1" -> insert();
                case "2" -> update();
                case "3" -> delete();
                case "4" -> getAll();
            }

        } while (!command.equals("exit"));

        out.println("\n*** Exiting Team Management ***\n");
    }

    private void getAll() {
        out.println("\n*** List of Teams ***\n");
        var teams = teamRepository.getAll();
        out.println(AsciiTable.getTable(teams, Arrays.asList(
                new Column().header("Id").with(team -> String.valueOf(team.getTeamId())),
                new Column().header("Name").with(Team::getName),
                new Column().header("Country").with(Team::getCountry),
                new Column().header("FoundationYear").with(team -> String.valueOf(team.getFoundationYear())),
                new Column().header("ContactInfo").with(Team::getContactInfo)
        )));
    }

    private void delete() {
        out.println("\n*** Delete Team ***\n");

        var team = modelFactory.createTeam();

        out.println("Enter the ID of the team to delete:");
        int id = Integer.parseInt(readLine(in));
        team.setTeamId(id);

        teamRepository.delete(team);
        out.println("\nSuccessfully deleted.\n");
    }

    private void update() {
        out.println("\n*** Update Client ***\n");

        try {
            var team = modelFactory.createTeam();

            out.println("Enter the ID of the client to update:");
            int id = Integer.parseInt(readLine(in));
            team.setTeamId(id);

            out.println("Enter new Name:");
            team.setName(readLine(in));

            out.println("Enter new Phone:");
            team.setContactInfo(readLine(in));

            teamRepository.save(team);
            out.println("\nSuccessfully updated.\n");

        } catch (NumberFormatException e) {
            out.println("\nInvalid team ID. Please enter a valid integer ID.\n");
        } catch (Exception e) {
            out.println("\nAn error occurred while updating the team: " + e.getMessage() + "\n");
        }
    }

    private void insert() {
        out.println("\n*** Insert Team ***\n");

        var team = modelFactory.createTeam();

        out.println("Enter the Name:");
        team.setName((readLine(in)));

        out.println("Enter the Country:");
        team.setCountry(readLine(in));

        out.println("Enter the Foundation Year:");
        team.setFoundationYear(Integer.parseInt(readLine(in)));

        out.println("Enter the Contact Information:");
        team.setContactInfo(readLine(in));

        teamRepository.save(team);
        out.println("\nSuccessfully inserted.\n");
    }

    private void showTeamMenu() {
        out.println("\n*** Team Management Menu ***\n");
        out.println("1. Insert Team");
        out.println("2. Update Team");
        out.println("3. Delete Team");
        out.println("4. Get All Teams");
        out.println("Type 'exit' to quit.");
        out.println();
    }
}
