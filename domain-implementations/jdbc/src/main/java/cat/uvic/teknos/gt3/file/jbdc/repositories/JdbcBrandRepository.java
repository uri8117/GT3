package cat.uvic.teknos.gt3.file.jbdc.repositories;

import cat.uvic.teknos.gt3.domain.models.Brand;
import cat.uvic.teknos.gt3.domain.repositories.BrandRepository;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class JdbcBrandRepository implements BrandRepository {
    private final Connection connection;

    public JdbcBrandRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Brand model) {
        if (model.getBrandId() <= 0) {
            insert(model);
        } else {
            update(model);
        }
    }

    private void insert(Brand model) {
        String sql = "INSERT INTO BRANDS (NAME, COUNTRY_OF_ORIGIN, CONTACT_INFO) VALUES (?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, model.getName());
            statement.setString(2, model.getCountryOfOrigin());
            statement.setString(3, model.getContactInfo());

            statement.executeUpdate();
            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    model.setBrandId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting brand", e);
        }
    }

    private void update(Brand model) {
        String sql = "UPDATE BRANDS SET NAME=?, COUNTRY_OF_ORIGIN=?, CONTACT_INFO=? WHERE BRAND_ID=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, model.getName());
            statement.setString(2, model.getCountryOfOrigin());
            statement.setString(3, model.getContactInfo());
            statement.setInt(4, model.getBrandId());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No brand to update");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating brand", e);
        }
    }

    @Override
    public void delete(Brand model) {
        String sql = "DELETE FROM BRANDS WHERE BRAND_ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, model.getBrandId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No brand to delete");
            } else {
                System.out.println("Brand deleted successfully");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting brand", e);
        }
    }

    @Override
    public Brand get(Integer id) {
        String sql = "SELECT * FROM BRANDS WHERE BRAND_ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Brand brand = new cat.uvic.teknos.gt3.file.jbdc.models.Brand();
                    brand.setBrandId(resultSet.getInt("BRAND_ID"));
                    brand.setName(resultSet.getString("NAME"));
                    brand.setCountryOfOrigin(resultSet.getString("COUNTRY_OF_ORIGIN"));
                    brand.setContactInfo(resultSet.getString("CONTACT_INFO"));
                    return brand;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving brand", e);
        }
    }

    @Override
    public Set<Brand> getAll() {
        String sql = "SELECT * FROM BRANDS";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            Set<Brand> brands = new HashSet<>();
            while (resultSet.next()) {
                Brand brand = new cat.uvic.teknos.gt3.file.jbdc.models.Brand();
                brand.setBrandId(resultSet.getInt("BRAND_ID"));
                brand.setName(resultSet.getString("NAME"));
                brand.setCountryOfOrigin(resultSet.getString("COUNTRY_OF_ORIGIN"));
                brand.setContactInfo(resultSet.getString("CONTACT_INFO"));
                brands.add(brand);
            }
            return brands;
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving brands", e);
        }
    }
}
