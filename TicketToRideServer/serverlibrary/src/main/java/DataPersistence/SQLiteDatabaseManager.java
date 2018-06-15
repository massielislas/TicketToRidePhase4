package DataPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Massiel on 6/14/2018.
 */

public class SQLiteDatabaseManager {

    public static SQLiteDatabaseManager databaseManager = null;

    private SQLiteDatabaseManager(){

    }

    public static SQLiteDatabaseManager getInstance(){
        if(databaseManager == null)
            databaseManager = new SQLiteDatabaseManager();

        return databaseManager;
    }

    public static Connection openConnection() throws SQLException{
        Connection connection;
        final String CONNECTION_URL = "jdbc:sqlite:FamilyServer.sqlite";
        //System.out.println(CONNECTION_URL);

        //Open a database connection
        connection = DriverManager.getConnection(CONNECTION_URL);

        connection.setAutoCommit(false);
        System.out.println("Connection to database established");
        return connection;
    }

    public static void closeConnection(boolean commit, Connection connection) throws SQLException{
        if(commit) {
            connection.commit();
        } else {
            connection.rollback();
        }
    }

}
