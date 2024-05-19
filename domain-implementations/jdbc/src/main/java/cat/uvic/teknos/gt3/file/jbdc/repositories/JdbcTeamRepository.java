package cat.uvic.teknos.gt3.file.jbdc.repositories;

import cat.uvic.teknos.gt3.domain.models.Team;
import cat.uvic.teknos.gt3.domain.repositories.TeamRepository;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class JdbcTeamRepository implements TeamRepository {
    private final Connection connection;

    public JdbcTeamRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Team model) {
        if (model.getTeamId() <= 0) {
            insert(model);
        } else {
            update(model);
        }
    }

    private void insert(Team model) {
        String sql = "INSERT INTO TEAMS (NAME, COUNTRY, FOUNDATION_YEAR, CONTACT_INFO) VALUES (?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, model.getName());
            statement.setString(2, model.getCountry());
            statement.setInt(3, model.getFoundationYear());
            statement.setString(4, model.getContactInfo());

            statement.executeUpdate();
            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    model.setTeamId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting team", e);
        }
    }

    private void update(Team model) {
        String sql = "UPDATE TEAMS SET NAME=?, COUNTRY=?, FOUNDATION_YEAR=?, CONTACT_INFO=? WHERE TEAM_ID=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, model.getName());
            statement.setString(2, model.getCountry());
            statement.setInt(3, model.getFoundationYear());
            statement.setString(4, model.getContactInfo());
            statement.setInt(5, model.getTeamId());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No team to update");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating team", e);
        }
    }

    @Override
    public void delete(Team model) {
        String sql = "DELETE FROM TEAMS WHERE TEAM_ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, model.getTeamId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No team to delete");
            } else {
                System.out.println("Team deleted successfully");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting team", e);
        }
    }

    @Override
    public Team get(Integer id) {
        String sql = "SELECT * FROM TEAMS WHERE TEAM_ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Team team = new cat.uvic.teknos.gt3.file.jbdc.models.Team();
                    team.setTeamId(resultSet.getInt("TEAM_ID"));
                    team.setName(resultSet.getString("NAME"));
                    team.setCountry(resultSet.getString("COUNTRY"));
                    team.setFoundationYear(resultSet.getInt("FOUNDATION_YEAR"));
                    team.setContactInfo(resultSet.getString("CONTACT_INFO"));
                    return team;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving team", e);
        }
    }

    @Override
    public Set<Team> getAll() {
        String sql = "SELECT * FROM TEAMS";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            Set<Team> teams = new HashSet<>();
            while (resultSet.next()) {
                Team team = new cat.uvic.teknos.gt3.file.jbdc.models.Team();
                team.setTeamId(resultSet.getInt("TEAM_ID"));
                team.setName(resultSet.getString("NAME"));
                team.setCountry(resultSet.getString("COUNTRY"));
                team.setFoundationYear(resultSet.getInt("FOUNDATION_YEAR"));
                team.setContactInfo(resultSet.getString("CONTACT_INFO"));
                teams.add(team);
            }
            return teams;
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving teams", e);
        }
    }
}
