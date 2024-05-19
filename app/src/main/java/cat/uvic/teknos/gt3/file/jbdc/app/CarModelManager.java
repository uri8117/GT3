package cat.uvic.teknos.gt3.file.jbdc.app;

import cat.uvic.teknos.gt3.domain.models.ModelFactory;
import cat.uvic.teknos.gt3.domain.models.CarModel;
import cat.uvic.teknos.gt3.domain.repositories.CarModelRepository;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.Arrays;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;

import static cat.uvic.teknos.gt3.file.jbdc.app.IOUtils.readLine;

public class CarModelManager {

    private final PrintStream out;
    private final BufferedReader in;
    private final CarModelRepository carModelRepository;
    private final ModelFactory modelFactory;

    public CarModelManager(BufferedReader in, PrintStream out, CarModelRepository carModelRepository, ModelFactory modelFactory) {
        this.out = out;
        this.in = in;
        this.carModelRepository = carModelRepository;
        this.modelFactory = modelFactory;
    }

    public void start() {
        out.println("\n*** Car Model Management ***\n");

        var command = "";
        do {
            showCarModelMenu();
            command = readLine(in);

            switch (command) {
                case "1" -> insert();
                case "2" -> update();
                case "3" -> delete();
                case "4" -> getAll();
            }

        } while (!command.equals("exit"));

        out.println("\n*** Exiting Car Model Management ***\n");
    }

    private void getAll() {
        out.println("\n*** List of Car Models ***\n");
        var carModels = carModelRepository.getAll();
        out.println(AsciiTable.getTable(carModels, Arrays.asList(
                new Column().header("Id").with(carModel -> String.valueOf(carModel.getModelId())),
                new Column().header("Brand Id").with(carModel -> String.valueOf(carModel.getBrandId())),
                new Column().header("Model").with(CarModel::getModel)
        )));
    }

    private void delete() {
        out.println("\n*** Delete Car Model ***\n");

        out.println("Enter the ID of the car model to delete:");
        int id = Integer.parseInt(readLine(in));

        var carModel = carModelRepository.get(id);
        if (carModel != null) {
            carModelRepository.delete(carModel);
            out.println("\nSuccessfully deleted.\n");
        } else {
            out.println("\nCar model not found.\n");
        }
    }

    private void update() {
        out.println("\n*** Update Car Model ***\n");

        out.println("Enter the ID of the car model to update:");
        int id = Integer.parseInt(readLine(in));

        var carModel = carModelRepository.get(id);
        if (carModel != null) {
            out.println("Enter new Brand Id:");
            carModel.setBrandId(Integer.parseInt(readLine(in)));

            out.println("Enter new Model:");
            carModel.setModel(readLine(in));

            carModelRepository.save(carModel);
            out.println("\nSuccessfully updated.\n");
        } else {
            out.println("\nCar model not found.\n");
        }
    }

    private void insert() {
        out.println("\n*** Insert Car Model ***\n");

        var carModel = modelFactory.createCarModel();

        out.println("Enter the Brand Id:");
        carModel.setBrandId(Integer.parseInt(readLine(in)));

        out.println("Enter the Model:");
        carModel.setModel(readLine(in));

        carModelRepository.save(carModel);
        out.println("\nSuccessfully inserted.\n");
    }

    private void showCarModelMenu() {
        out.println("\n*** Car Model Management Menu ***\n");
        out.println("1. Insert Car Model");
        out.println("2. Update Car Model");
        out.println("3. Delete Car Model");
        out.println("4. Get All Car Models");
        out.println("Type 'exit' to quit.");
        out.println();
    }
}
