package cat.uvic.teknos.gt3.file.jbdc.app;

public class RaceResultManager {

    /*private final PrintStream out;
    private final BufferedReader in;
    private final RaceResultRepository raceResultRepository;

    public RaceResultManager(BufferedReader in, PrintStream out, RaceResultRepository raceResultRepository) {
        this.out = out;
        this.in = in;
        this.raceResultRepository = raceResultRepository;
    }

    public void start() {
        out.println("\n*** Race Result Management ***\n");

        var command = "";
        do {
            showRaceResultMenu();
            command = readLine(in);

            switch (command) {
                case "1" -> insert();
                case "2" -> update();
                case "3" -> delete();
                case "4" -> getAll();
            }

        } while (!command.equals("exit"));

        out.println("\n*** Exiting Race Result Management ***\n");
    }

    private void getAll() {
        out.println("\n*** List of Race Results ***\n");
        var raceResults = raceResultRepository.getAll();
        out.println(AsciiTable.getTable(raceResults, Arrays.asList(
                new Column().header("Result Id").with(raceResult -> String.valueOf(raceResult.getResultId())),
                new Column().header("Race Id").with(raceResult -> String.valueOf(raceResult.getRaceId())),
                new Column().header("Team Id").with(raceResult -> String.valueOf(raceResult.getTeamId())),
                new Column().header("Driver Id").with(raceResult -> String.valueOf(raceResult.getDriverId())),
                new Column().header("Position").with(raceResult -> String.valueOf(raceResult.getPosition()))
        )));
    }

    private void delete() {
        out.println("\n*** Delete Race Result ***\n");

        out.println("Enter the Result ID:");
        int resultId = Integer.parseInt(readLine(in));

        var raceResult = raceResultRepository.get(resultId);
        if (raceResult != null) {
            raceResultRepository.delete(raceResult);
            out.println("\nSuccessfully deleted.\n");
        } else {
            out.println("\nRace result not found.\n");
        }
    }

    private void update() {
        out.println("\n*** Update Race Result ***\n");

        out.println("Enter the Result ID:");
        int resultId = Integer.parseInt(readLine(in));

        var raceResult = raceResultRepository.get(resultId);
        if (raceResult != null) {
            out.println("Enter new Race ID:");
            raceResult.setRaceId(Integer.parseInt(readLine(in)));

            out.println("Enter new Team ID:");
            raceResult.setTeamId(Integer.parseInt(readLine(in)));

            out.println("Enter new Driver ID:");
            raceResult.setDriverId(Integer.parseInt(readLine(in)));

            out.println("Enter new Position:");
            raceResult.setPosition(Integer.parseInt(readLine(in)));

            raceResultRepository.save(raceResult);
            out.println("\nSuccessfully updated.\n");
        } else {
            out.println("\nRace result not found.\n");
        }
    }

    private void insert() {
        out.println("\n*** Insert Race Result ***\n");

        var raceResult = new RaceResult();

        out.println("Enter the Race ID:");
        raceResult.setRaceId(Integer.parseInt(readLine(in)));

        out.println("Enter the Team ID:");
        raceResult.setTeamId(Integer.parseInt(readLine(in)));

        out.println("Enter the Driver ID:");
        raceResult.setDriverId(Integer.parseInt(readLine(in)));

        out.println("Enter the Position:");
        raceResult.setPosition(Integer.parseInt(readLine(in)));

        raceResultRepository.save(raceResult);
        out.println("\nSuccessfully inserted.\n");
    }

    private void showRaceResultMenu() {
        out.println("\n*** Race Result Management Menu ***\n");
        out.println("1. Insert Race Result");
        out.println("2. Update Race Result");
        out.println("3. Delete Race Result");
        out.println("4. Get All Race Results");
        out.println("Type 'exit' to quit.");
        out.println();
    }*/
}
