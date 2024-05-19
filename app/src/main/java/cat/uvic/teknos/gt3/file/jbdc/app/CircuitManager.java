package cat.uvic.teknos.gt3.file.jbdc.app;

import cat.uvic.teknos.gt3.domain.models.ModelFactory;
import cat.uvic.teknos.gt3.domain.models.Circuit;
import cat.uvic.teknos.gt3.domain.repositories.CircuitRepository;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.Arrays;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;

import static cat.uvic.teknos.gt3.file.jbdc.app.IOUtils.readLine;

public class CircuitManager {

    private final PrintStream out;
    private final BufferedReader in;
    private final CircuitRepository circuitRepository;
    private final ModelFactory modelFactory;

    public CircuitManager(BufferedReader in, PrintStream out, CircuitRepository circuitRepository, ModelFactory modelFactory) {
        this.out = out;
        this.in = in;
        this.circuitRepository = circuitRepository;
        this.modelFactory = modelFactory;
    }

    public void start() {
        out.println("\n*** Circuit Management ***\n");

        var command = "";
        do {
            showCircuitMenu();
            command = readLine(in);

            switch (command) {
                case "1" -> insert();
                case "2" -> update();
                case "3" -> delete();
                case "4" -> getAll();
            }

        } while (!command.equals("exit"));

        out.println("\n*** Exiting Circuit Management ***\n");
    }

    private void getAll() {
        out.println("\n*** List of Circuits ***\n");
        var circuits = circuitRepository.getAll();
        out.println(AsciiTable.getTable(circuits, Arrays.asList(
                new Column().header("Id").with(circuit -> String.valueOf(circuit.getCircuitId())),
                new Column().header("Name").with(Circuit::getName),
                new Column().header("Country").with(Circuit::getCountry),
                new Column().header("Length").with(circuit -> String.valueOf(circuit.getLength())),
                new Column().header("Type").with(Circuit::getType)
        )));
    }

    private void delete() {
        out.println("\n*** Delete Circuit ***\n");

        out.println("Enter the ID of the circuit to delete:");
        int id = Integer.parseInt(readLine(in));

        var circuit = circuitRepository.get(id);
        if (circuit != null) {
            circuitRepository.delete(circuit);
            out.println("\nSuccessfully deleted.\n");
        } else {
            out.println("\nCircuit not found.\n");
        }
    }

    private void update() {
        out.println("\n*** Update Circuit ***\n");

        out.println("Enter the ID of the circuit to update:");
        int id = Integer.parseInt(readLine(in));

        var circuit = circuitRepository.get(id);
        if (circuit != null) {
            out.println("Enter new Name:");
            circuit.setName(readLine(in));

            out.println("Enter new Country:");
            circuit.setCountry(readLine(in));

            out.println("Enter new Length:");
            circuit.setLength(Float.parseFloat(readLine(in)));

            out.println("Enter new Type:");
            circuit.setType(readLine(in));

            circuitRepository.save(circuit);
            out.println("\nSuccessfully updated.\n");
        } else {
            out.println("\nCircuit not found.\n");
        }
    }

    private void insert() {
        out.println("\n*** Insert Circuit ***\n");

        var circuit = modelFactory.createCircuit();

        out.println("Enter the Name:");
        circuit.setName(readLine(in));

        out.println("Enter the Country:");
        circuit.setCountry(readLine(in));

        out.println("Enter the Length:");
        circuit.setLength(Float.parseFloat(readLine(in)));

        out.println("Enter the Type:");
        circuit.setType(readLine(in));

        circuitRepository.save(circuit);
        out.println("\nSuccessfully inserted.\n");
    }

    private void showCircuitMenu() {
        out.println("\n*** Circuit Management Menu ***\n");
        out.println("1. Insert Circuit");
        out.println("2. Update Circuit");
        out.println("3. Delete Circuit");
        out.println("4. Get All Circuits");
        out.println("Type 'exit' to quit.");
        out.println();
    }
}
