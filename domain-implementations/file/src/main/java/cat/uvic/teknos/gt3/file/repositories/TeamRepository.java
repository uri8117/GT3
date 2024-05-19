package cat.uvic.teknos.gt3.file.repositories;

import cat.uvic.teknos.gt3.domain.models.Team;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class TeamRepository implements cat.uvic.teknos.gt3.domain.repositories.TeamRepository {

    private static Map<Integer, Team> teams = new HashMap<>();

    private String path;

    public TeamRepository(String path) {
        this.path = path;
    }

    // Method to load employees from file
    public void load() {
        //var currentDirectory = System.getProperty("user.dir") + "/src/main/resources/";

        if (Files.exists(Path.of(path))) {
            try (var inputStream = new ObjectInputStream(new FileInputStream(path))) {
                teams = (Map<Integer, Team>) inputStream.readObject();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Method to write employees to file
    void write() {
        //var currentDirectory = System.getProperty("user.dir") + "/src/main/resources/";

        try (var outputStream = new ObjectOutputStream(new FileOutputStream(path))) {
            outputStream.writeObject(teams);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to save an employee
    @Override
    public void save(Team model) {
        if (model.getTeamId() <= 0) {
            // generate new id
            var newId = teams.keySet().stream().mapToInt(k -> k).max().orElse(0) + 1;
            teams.put(newId, model);
        } else {
            teams.put(model.getTeamId(), model);
        }
        write();
    }



    public void update() {
        //var currentDirectory = System.getProperty("user.dir") + "/src/main/resources/";

        try (var outputStream = new ObjectOutputStream(new FileOutputStream(path))) {
            outputStream.writeObject(teams);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to delete an employee
    @Override
    public void delete(Team model) {
        //var currentDirectory = System.getProperty("user.dir") + "/src/main/resources/";

        try (var outputStream = new ObjectOutputStream(new FileOutputStream(path))) {

            for (Iterator<Map.Entry<Integer, Team>> iterator = teams.entrySet().iterator(); iterator.hasNext(); ) {
                Map.Entry<Integer, Team> entry = iterator.next();
                if (entry.getValue().equals(model)) {
                    iterator.remove();
                    break;
                }
            }
            outputStream.writeObject(teams);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to get an employee by id
    @Override
    public Team get(Integer id) {
        return teams.get(id);
    }

    // Method to get all employees
    @Override
    public Set<Team> getAll() {
        return Set.copyOf(teams.values());
    }
}
