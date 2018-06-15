package DataPersistence;

import com.google.gson.reflect.TypeToken;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Communication.Encoder;
import Model.Command;
import Model.Game;

/**
 * Created by madeleineaydelotte on 6/14/18.
 */

public class SQLiteGameDAO implements IGameDAO {

    private static Encoder encoder = new Encoder();

    @Override
    public boolean clearGames() {
        Connection connection = null;
        boolean success = false;
        try{
            connection = SQLiteDatabaseManager.openConnection();
            Statement statement = null;
            try{
                statement = connection.createStatement();
                statement.executeUpdate("DELETE FROM Game");
                System.out.println("Clearing games executed successfully");
                success = true;
            } finally{
                if(statement != null){
                    statement.close();
                    statement = null;
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
            success = false;
        }finally {
            if(connection != null){
                SQLiteDatabaseManager.closeConnection(true, connection);
                connection = null;
            }
        }
        return  success;
    }

    @Override
    public List<Game> loadGames() {
        System.out.println("getting games from database");
        ResultSet resultSet = null;
        boolean resultSetIsEmpty = true;
        Connection connection = null;
        String sqlQuery = "SELECT * FROM Game";
        Game game = null;
        List<Game>games = new ArrayList<>();
        boolean success = false;

        try{
            connection = SQLiteDatabaseManager.openConnection();
            PreparedStatement statement = null;
            try{
                statement = connection.prepareStatement(sqlQuery);
                resultSet = statement.executeQuery();
                while(resultSet.next()){
                    game = (Game) encoder.Decode(resultSet.getString("game"), Game.class);
                    games.add(game);
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
        } finally{
            if(connection != null){
                SQLiteDatabaseManager.closeConnection(success, connection);
                connection = null;
            }
        }
        return games;
    }

    @Override
    public boolean addGame(Game game) {
        System.out.println("Adding game to database...");

        Connection connection = null;
        boolean success = false;
        String sqlStatement = "";
        String json = encoder.Encode(game);

        sqlStatement += "INSERT INTO Game(gameID, game)" + '\n';
        sqlStatement += "Values('" + game.getID() + "',";
        sqlStatement += "'" + json  + "')";

        try{
            connection = SQLiteDatabaseManager.openConnection();
            Statement statement = null;
            try{
                statement = connection.createStatement();
                statement.executeUpdate(sqlStatement);
                System.out.println("Inserted Game successfully");
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

    @Override
    public boolean updateGameState(Game game) {
        System.out.println("Setting game state...");
        boolean success = false;
        String sqlStatement = "";
        Connection connection = null;
        String json = encoder.Encode(game);
        sqlStatement += "UPDATE Game Set game=" + json + "' WHERE gameID='" + game.getID() + "'";
        try{
            connection = SQLiteDatabaseManager.openConnection();
            Statement statement = null;
            try{
                statement = connection.createStatement();
                statement.executeUpdate(sqlStatement);
                System.out.println("Updated game state successfully");
                success = true;
            } finally{
                if(statement != null){
                    statement.close();
                    statement = null;
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
            success = false;
        }finally{
            if(connection != null){
                SQLiteDatabaseManager.closeConnection(true, connection);
                connection = null;
            }
        }
        return success;
    }

    @Override
    public boolean updateCommandsForGame(Game game, List<Command> commands) {
        System.out.println("Setting game state...");
        boolean success = false;
        String sqlStatement = "";
        Connection connection = null;
        String json = encoder.Encode(commands);
        sqlStatement += "UPDATE Game Set commands=" + json + "' WHERE gameID='" + game.getID() + "'";
        try{
            connection = SQLiteDatabaseManager.openConnection();
            Statement statement = null;
            try{
                statement = connection.createStatement();
                statement.executeUpdate(sqlStatement);
                System.out.println("Updated game commands successfully");
                success = true;
            } finally{
                if(statement != null){
                    statement.close();
                    statement = null;
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
            success = false;
        }finally{
            if(connection != null){
                SQLiteDatabaseManager.closeConnection(true, connection);
                connection = null;
            }
        }
        return success;
    }

    @Override
    public List<Command> loadCommands(Game game) {
        System.out.println("getting games from database");
        ResultSet resultSet = null;
        boolean resultSetIsEmpty = true;
        Connection connection = null;
        String sqlQuery = "SELECT * FROM Game where gameID=" + game.getID();
        List<Command>commands = new ArrayList<>();
        boolean success = false;

        try{
            connection = SQLiteDatabaseManager.openConnection();
            PreparedStatement statement = null;
            try{
                statement = connection.prepareStatement(sqlQuery);
                resultSet = statement.executeQuery();
                while(resultSet.next()){
                    commands = (List<Command>) encoder.Decode(resultSet.getString("commands"), new TypeToken<ArrayList<Command>>(){}.getType());
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
            success = false;
        } finally{
            if(connection != null){
                SQLiteDatabaseManager.closeConnection(success, connection);
                connection = null;
            }
        }
        return commands;
    }
}
