package DataPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Massiel on 6/14/2018.
 */

public class SQLiteDatabaseManager {
    static {
        try{
            final String driver = "org.sqlite.JDBC";
            Class.forName(driver);
        } catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

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
        final String CONNECTION_URL = "jdbc:sqlite:TicketToRide.sqlite";

        //Open a database connection
        connection = DriverManager.getConnection(CONNECTION_URL);

        connection.setAutoCommit(false);
        System.out.println("Connection to database established");
        return connection;
    }

    public static void closeConnection(boolean commit, Connection connection){
        try{
            if(commit) {
                connection.commit();
            } else {
                connection.rollback();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
