package cat.uvic.teknos.gt3.file.jbdc.repositories;

import cat.uvic.teknos.gt3.domain.models.Driver;
import cat.uvic.teknos.gt3.domain.repositories.CarRepository;
import cat.uvic.teknos.gt3.domain.models.Car;
import cat.uvic.teknos.gt3.domain.repositories.CarRepository;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;


public class JdbcCarRepository implements CarRepository {

    private static final String INSERT_CAR = "INSERT INTO CAR (ID_BRAND, MODEL_NAME) VALUES (?,?)";
    //private static final String INSERT_DRIVER = "INSERT INTO DRIVER (FIRST_NAME, LAST_NAME, NATIONALITY, BITRHDATE) VALUES (?,?,?,?)";
    private static final String INSERT_CAR_DRIVER = "INSERT INTO CAR_DRIVER (ID_CAR, ID_DRIVER) VALUES (?,?)";

    private final Connection connection;

    public JdbcCarRepository(Connection connection){
        this.connection = connection;
    }

    @Override
    public void save(Car model) {
        if(model.getId()<=0){
            insert(model);
        }else{
            update(model);
        }
    }

    private void insert(Car model){
        try(var preparedStatement = connection.prepareStatement(INSERT_CAR, Statement.RETURN_GENERATED_KEYS);){
            connection.setAutoCommit(false);
            preparedStatement.setInt(1, model.getBrand().getId());
            preparedStatement.setString(2, model.getModelName());
            preparedStatement.executeUpdate();

            var keys = preparedStatement.getGeneratedKeys();
            if(keys.next()){
                model.setId(keys.getInt(1));
            } else {
                throw new SQLException("Creating race failed, no ID obtained.");
            }

            if (model.getDrivers() != null) {
                for (Driver driver : model.getDrivers()) {
                    InsertCarDriver(model.getId(), driver);
                }
            }

            connection.commit();
        } catch (SQLException e) {
            rollback();
            throw new RuntimeException(e);
        } finally{
            setAutocommitFalse();
        }

    }

    private void InsertCarDriver(int carId, Driver model) {
        try (
                var preparedStatement = connection.prepareStatement(INSERT_CAR_DRIVER)
        ) {
            preparedStatement.setInt(1, carId);
            preparedStatement.setInt(2, model.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Car model) {

    }

    @Override
    public void delete(Car model) {

    }

    @Override
    public Car get(Integer id) {
        return null;
    }

    @Override
    public Set<Car> getAll() {
        return Set.of();
    }

    //    private void update(User model){
//        try(
//                var preparedStatement = connection.prepareStatement("UPDATE USER SET USERNAME = (?) WHERE ID_USER = (?)");
//                //var userDataStatement = connection.prepareStatement("UPDATE USER_DATA SET USER_NAME = (?) WHERE ID_USER = (?)")
//        ){
//            connection.setAutoCommit(false);
//
//            var id = model.getId();
//            //Will not update User unless there's data, but will still check for UserData
//            //Since we only are updating username it will be the only thing to will check
//            if(model.getUsername()!=null){
//                preparedStatement.setString(1, model.getUsername());
//                preparedStatement.setInt(2, id);
//                preparedStatement.executeUpdate();
//            }
//
//
//            if(model.getUserData()!=null){
//                saveUser(model.getUserData(), id);
//                /*
//                userDataStatement.setInt(2, model.getId());
//                userDataStatement.setString(1,model.getUserData().getUserName());
//                userDataStatement.executeUpdate();*/
//            }
//            connection.commit();
//        }catch(SQLException e){
//            rollback();
//            throw new RuntimeException(e);
//        }finally{
//            setAutocommitFalse();
//        }
//
//    }
//
//
//    private void insertUserData(com.esori.list.models.UserData model, int id) {
//        try(
//                var userDataStatement = connection.prepareStatement(INSERT_USER_DATA)){
//            userDataStatement.setInt(1, id);
//            userDataStatement.setString(2,model.getUserName());
//            userDataStatement.setInt(3,model.getPhoneNumber());
//            userDataStatement.setString(4,model.getCountry());
//            userDataStatement.setInt(5,model.getAge());
//            userDataStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//    private void updateUserData(com.esori.list.models.UserData model, int id) {
//        try(var userDataStatement = connection.prepareStatement("UPDATE USER_DATA SET USER_NAME = (?), " +
//                "PHONE_NUM = ?, COUNTRY = ?, AGE = ? WHERE ID_USER = (?)")){
//            userDataStatement.setInt(5, id);
//            userDataStatement.setString(1,model.getUserName());
//            userDataStatement.setInt(2,model.getPhoneNumber());
//            userDataStatement.setString(3,model.getCountry());
//            userDataStatement.setInt(4,model.getAge());
//            userDataStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    @Override
//    public void delete(User model) {
//        try(PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM USER where ID_USER = ?")
//        ) {
//
//            //Since the schema has 'ON DELETE CASCADE' on every reference to foreign keys, we only need to Delete User
//            connection.setAutoCommit(false);
//            preparedStatement.setInt(1,model.getId());
//
//            preparedStatement.executeUpdate();
//            connection.commit();
//
//        } catch (SQLException e) {
//            rollback();
//            throw new RuntimeException(e);
//        } finally {
//            setAutocommitFalse();
//        }
//
//    }
//
//    @Override
//    public User get(Integer id) {
//
//        String query = "SELECT * FROM USER WHERE ID_USER = ?";
//        String queryUserData = "SELECT * FROM USER_DATA WHERE ID_USER = ?";
//
//        try (PreparedStatement statement = connection.prepareStatement(query);
//             var userDataStatement = connection.prepareStatement(queryUserData)) {
//
//            statement.setInt(1, id);
//            userDataStatement.setInt(1, id);
//            var resultSet = statement.executeQuery();
//
//            User result = getUser(userDataStatement, resultSet);
//            if (result != null) return result;
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        return null;
//    }
//
//
//    @Override
//    public User getByUsername(String username) {
//        String query = "SELECT * FROM USER WHERE USERNAME = ?";
//        String queryUserData =  "SELECT * FROM USER_DATA WHERE ID_USER = " +
//                "(SELECT ID_USER FROM USER WHERE USERNAME = ?)";
//        try (PreparedStatement statement = connection.prepareStatement(query);
//             var userDataStatement = connection.prepareStatement(queryUserData)) {
//
//            statement.setString(1, username);
//            userDataStatement.setString(1, username);
//
//            try (ResultSet resultSet = statement.executeQuery()) {
//
//                User result = getUser(userDataStatement, resultSet);
//                if (result != null) return result;
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        return null;
//    }
//
//
//    @Override
//    public Set<User> getAll() {
//        try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM USER");
//            var userDataStatement = connection.prepareStatement("SELECT * FROM USER_DATA")) {
//
//            var users = new HashSet<User>();
//            var resultSet = preparedStatement.executeQuery();
//            var resultSetData = userDataStatement.executeQuery();
//
//            while (resultSet.next()) {
//                User result = new cat.uvic.teknos.musicrep.domain.jdbc.models.User();
//                result.setId(resultSet.getInt("ID_USER"));
//                result.setUsername(resultSet.getString("USERNAME"));
//
//                getUserData(resultSetData, result);
//                users.add(result);
//            }
//            return users;
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//
//
//
//
//
//    private User getUser(PreparedStatement userDataStatement, ResultSet resultSet) throws SQLException {
//        var resultSetData = userDataStatement.executeQuery();
//        if (resultSet.next()) {
//
//            User result = new cat.uvic.teknos.musicrep.domain.jdbc.models.User();
//            result.setId(resultSet.getInt("ID_USER"));
//            result.setUsername(resultSet.getString("USERNAME"));
//
//            getUserData(resultSetData, result);
//
//            return result;
//        }
//        return null;
//    }
//
//    private void getUserData(ResultSet resultSetData, User user) throws SQLException {
//        if(resultSetData.next()){
//            UserData userData = new UserData();
//            userData.setUserName(resultSetData.getString("USER_NAME"));
//            userData.setAge(resultSetData.getInt("AGE"));
//            userData.setCountry(resultSetData.getString("COUNTRY"));
//            userData.setPhoneNumber(resultSetData.getInt("PHONE_NUM"));
//            user.setUserData(userData);
//        }
//    }
//
    private void setAutocommitFalse() {
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void rollback() {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
