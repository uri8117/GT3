package cat.uvic.teknos.gt3.file.jbdc.app;

import cat.uvic.teknos.gt3.domain.models.Car;
import cat.uvic.teknos.gt3.domain.models.ModelFactory;
import cat.uvic.teknos.gt3.domain.repositories.CarRepository;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.Arrays;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;

import static cat.uvic.teknos.gt3.file.jbdc.app.IOUtils.readLine;

public class CarManager {

    private final PrintStream out;
    private final BufferedReader in;
    private final CarRepository carRepository;
    private final ModelFactory modelFactory;

    public CarManager(BufferedReader in, PrintStream out, CarRepository carRepository, ModelFactory modelFactory) {
        this.out = out;
        this.in = in;
        this.carRepository = carRepository;
        this.modelFactory = modelFactory;
    }

    public void start() {
        out.println("\n*** Car Management ***\n");

        var command = "";
        do {
            showCarMenu();
            command = readLine(in);

            switch (command) {
                case "1" -> insert();
                case "2" -> update();
                case "3" -> delete();
                case "4" -> getAll();
            }

        } while (!command.equals("exit"));

        out.println("\n*** Exiting Car Management ***\n");
    }

    private void getAll() {
        out.println("\n*** List of Cars ***\n");
        var cars = carRepository.getAll();
        out.println(AsciiTable.getTable(cars, Arrays.asList(
                new Column().header("Id").with(car -> String.valueOf(car.getCarId())),
                new Column().header("Brand").with(Car::getBrand),
                new Column().header("Model").with(Car::getModel),
                new Column().header("Manufacturing Year").with(car -> String.valueOf(car.getManufacturingYear())),
                new Column().header("Power").with(car -> String.valueOf(car.getPower())),
                new Column().header("Weight").with(car -> String.valueOf(car.getWeight())),
                new Column().header("Engine Type").with(Car::getEngineType),
                new Column().header("Chassis Manufacturer").with(Car::getChassisManufacturer)
        )));
    }

    private void delete() {
        out.println("\n*** Delete Car ***\n");

        out.println("Enter the ID of the car to delete:");
        int id = Integer.parseInt(readLine(in));

        var car = carRepository.get(id);
        if (car != null) {
            carRepository.delete(car);
            out.println("\nSuccessfully deleted.\n");
        } else {
            out.println("\nCar not found.\n");
        }
    }

    private void update() {
        out.println("\n*** Update Car ***\n");

        out.println("Enter the ID of the car to update:");
        int id = Integer.parseInt(readLine(in));

        var car = carRepository.get(id);
        if (car != null) {
            out.println("Enter new Brand:");
            car.setBrand(readLine(in));

            out.println("Enter new Model:");
            car.setModel(readLine(in));

            out.println("Enter new Manufacturing Year:");
            car.setManufacturingYear(Integer.parseInt(readLine(in)));

            out.println("Enter new Power:");
            car.setPower(Integer.parseInt(readLine(in)));

            out.println("Enter new Weight:");
            car.setWeight(Integer.parseInt(readLine(in)));

            out.println("Enter new Engine Type:");
            car.setEngineType(readLine(in));

            out.println("Enter new Chassis Manufacturer:");
            car.setChassisManufacturer(readLine(in));

            carRepository.save(car);
            out.println("\nSuccessfully updated.\n");
        } else {
            out.println("\nCar not found.\n");
        }
    }

    private void insert() {
        out.println("\n*** Insert Car ***\n");

        var car = modelFactory.createCar();

        out.println("Enter the Brand:");
        car.setBrand(readLine(in));

        out.println("Enter the Model:");
        car.setModel(readLine(in));

        out.println("Enter the Manufacturing Year:");
        car.setManufacturingYear(Integer.parseInt(readLine(in)));

        out.println("Enter the Power:");
        car.setPower(Integer.parseInt(readLine(in)));

        out.println("Enter the Weight:");
        car.setWeight(Integer.parseInt(readLine(in)));

        out.println("Enter the Engine Type:");
        car.setEngineType(readLine(in));

        out.println("Enter the Chassis Manufacturer:");
        car.setChassisManufacturer(readLine(in));

        carRepository.save(car);
        out.println("\nSuccessfully inserted.\n");
    }

    private void showCarMenu() {
        out.println("\n*** Car Management Menu ***\n");
        out.println("1. Insert Car");
        out.println("2. Update Car");
        out.println("3. Delete Car");
        out.println("4. Get All Cars");
        out.println("Type 'exit' to quit.");
        out.println();
    }
}
