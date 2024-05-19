package cat.uvic.teknos.gt3.file.jbdc.app;

import cat.uvic.teknos.gt3.domain.models.ModelFactory;
import cat.uvic.teknos.gt3.domain.models.Brand;
import cat.uvic.teknos.gt3.domain.repositories.BrandRepository;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.Arrays;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;

import static cat.uvic.teknos.gt3.file.jbdc.app.IOUtils.readLine;

public class BrandManager {

    private final PrintStream out;
    private final BufferedReader in;
    private final BrandRepository brandRepository;
    private final ModelFactory modelFactory;

    public BrandManager(BufferedReader in, PrintStream out, BrandRepository brandRepository, ModelFactory modelFactory) {
        this.out = out;
        this.in = in;
        this.brandRepository = brandRepository;
        this.modelFactory = modelFactory;
    }

    public void start() {
        out.println("\n*** Brand Management ***\n");

        var command = "";
        do {
            showBrandMenu();
            command = readLine(in);

            switch (command) {
                case "1" -> insert();
                case "2" -> update();
                case "3" -> delete();
                case "4" -> getAll();
            }

        } while (!command.equals("exit"));

        out.println("\n*** Exiting Brand Management ***\n");
    }

    private void getAll() {
        out.println("\n*** List of Brands ***\n");
        var brands = brandRepository.getAll();
        out.println(AsciiTable.getTable(brands, Arrays.asList(
                new Column().header("Id").with(brand -> String.valueOf(brand.getBrandId())),
                new Column().header("Name").with(Brand::getName),
                new Column().header("Country of Origin").with(Brand::getCountryOfOrigin),
                new Column().header("Contact Info").with(Brand::getContactInfo)
        )));
    }

    private void delete() {
        out.println("\n*** Delete Brand ***\n");

        out.println("Enter the ID of the brand to delete:");
        int id = Integer.parseInt(readLine(in));

        var brand = brandRepository.get(id);
        if (brand != null) {
            brandRepository.delete(brand);
            out.println("\nSuccessfully deleted.\n");
        } else {
            out.println("\nBrand not found.\n");
        }
    }

    private void update() {
        out.println("\n*** Update Brand ***\n");

        out.println("Enter the ID of the brand to update:");
        int id = Integer.parseInt(readLine(in));

        var brand = brandRepository.get(id);
        if (brand != null) {
            out.println("Enter new Name:");
            brand.setName(readLine(in));

            out.println("Enter new Country of Origin:");
            brand.setCountryOfOrigin(readLine(in));

            out.println("Enter new Contact Info:");
            brand.setContactInfo(readLine(in));

            brandRepository.save(brand);
            out.println("\nSuccessfully updated.\n");
        } else {
            out.println("\nBrand not found.\n");
        }
    }

    private void insert() {
        out.println("\n*** Insert Brand ***\n");

        var brand = modelFactory.createBrand();

        out.println("Enter the Name:");
        brand.setName(readLine(in));

        out.println("Enter the Country of Origin:");
        brand.setCountryOfOrigin(readLine(in));

        out.println("Enter the Contact Info:");
        brand.setContactInfo(readLine(in));

        brandRepository.save(brand);
        out.println("\nSuccessfully inserted.\n");
    }

    private void showBrandMenu() {
        out.println("\n*** Brand Management Menu ***\n");
        out.println("1. Insert Brand");
        out.println("2. Update Brand");
        out.println("3. Delete Brand");
        out.println("4. Get All Brands");
        out.println("Type 'exit' to quit.");
        out.println();
    }
}
