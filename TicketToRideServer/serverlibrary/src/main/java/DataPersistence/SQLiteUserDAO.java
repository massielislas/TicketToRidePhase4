package DataPersistence;

import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Communication.Encoder;
import Model.User;
import Results.Result;

/**
 * Created by madeleineaydelotte on 6/14/18.
 */

public class SQLiteUserDAO implements IUserDAO {

    private static Encoder encoder = new Encoder();

    @Override
    public List<User> loadUsers() {
        ResultSet resultSet = null;
        boolean resultSetIsEmpty = true;
        Connection connection = null;
        String sqlQuery = "SELECT * FROM User";
        User user = null;
        List<User>users = new ArrayList<>();
        boolean success = false;

        try{
            connection = SQLiteDatabaseManager.openConnection();
            PreparedStatement statement = null;
            try{
                statement = connection.prepareStatement(sqlQuery);
                resultSet = statement.executeQuery();
                while(resultSet.next()){
                    user = (User) encoder.Decode(resultSet.getString("user"), User.class);
                    users.add(user);
                    success = true;

                }
            } finally{
                if(statement != null){
                    statement.close();
                    statement = null;
                }
            }
        } catch(SQLException e){
            e.printStackTrace();
            return null;
        } finally{
            if(connection != null){
                SQLiteDatabaseManager.closeConnection(success, connection);
                connection = null;
            }
        }
        return users;
    }

    @Override
    public boolean addUser(User user){
        System.out.println("Adding user to database...");

        Connection connection = null;
        boolean success = false;
        String sqlStatement = "";
        String json = encoder.Encode(user);

        sqlStatement += "INSERT INTO User(username, user)" + '\n';
        sqlStatement += "Values('" + user.getUserName()+ "',";
        sqlStatement += "'" + json + "')";

        try{
            connection = SQLiteDatabaseManager.openConnection();
            Statement statement = null;
            try{
                statement = connection.createStatement();
                statement.executeUpdate(sqlStatement);
                System.out.println("Inserted User successfully");
                success = true;
            } finally{
                if(statement != null){
                    statement.close();
                    statement = null;
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }finally{
            if(connection != null){
                SQLiteDatabaseManager.closeConnection(success, connection);
                connection = null;
            }
        }
        return success;
    }
}
