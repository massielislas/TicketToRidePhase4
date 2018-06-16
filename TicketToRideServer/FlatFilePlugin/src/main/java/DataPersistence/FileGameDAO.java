package DataPersistence;

import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

import Communication.Encoder;
import Model.Command;
import Model.Game;

/**
 * Created by madeleineaydelotte on 6/14/18.
 */

public class FileGameDAO implements IGameDAO {

    private String commandFileName;
    private String gameFileName;
    private Encoder encoder;

    public static FileGameDAO getInstance() {
        return new FileGameDAO();
    }

    public FileGameDAO () {
        this.encoder = new Encoder();

        this.commandFileName = "/FlatFilePlugin/src/main/java/DataPersistence/commands.txt";
        this.gameFileName = "/FlatFilePlugin/src/main/java/DataPersistence/games.txt";
    }

    @Override
    public List<Game> loadGames() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(gameFileName));
            String response = new String();
            for (String line; (line = br.readLine()) != null; response += line);
            br.close();

            List<Game> games = (ArrayList<Game>) encoder.Decode(response, new TypeToken<ArrayList<Game>>(){}.getType());

            if (games == null) {
                return new ArrayList<Game>(0);
            }

            return games;

        } catch (Exception e) {
            System.out.println("Exception loading games in FileGameDAO");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            return null;
        }
    }

    @Override
    public List<Command> loadCommands (Game game) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(commandFileName));
            String response = new String();
            for (String line; (line = br.readLine()) != null; response += line);
            br.close();


            ArrayList<AbstractMap.SimpleEntry<String, ArrayList<Command>>> commandList = (ArrayList<AbstractMap.SimpleEntry<String, ArrayList<Command>>>) encoder.Decode(response, new TypeToken<ArrayList<AbstractMap.SimpleEntry<String, ArrayList<Command>>>>(){}.getType());

            if (commandList == null) {
                return new ArrayList<Command>(0);
            }

            for (int i = 0; i < commandList.size(); ++i) {
                if (commandList.get(i).getKey().equals(game.getID())) {
                    return commandList.get(i).getValue();
                }
            }

            return new ArrayList<Command>(0);

        } catch (Exception e) {
            System.out.println("Exception loading commands in FileGameDAO");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            return null;
        }
    }

    @Override
    public boolean addGame(Game game) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(gameFileName));
            String response = new String();
            for (String line; (line = br.readLine()) != null; response += line);
            br.close();

            ArrayList<Game> games = (ArrayList<Game>) encoder.Decode(response, new TypeToken<ArrayList<Game>>(){}.getType());

            if (games == null) {
                games = new ArrayList<Game>(0);
            }

            games.add(game);

            String gamesJSON = encoder.Encode(games);

            BufferedWriter bw = new BufferedWriter(new FileWriter(gameFileName));
            bw.write(gamesJSON);
            bw.close();

            return true;

        } catch (Exception e) {
            System.out.println("Exception adding game in FileGameDAO");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            return false;
        }
    }

    @Override
    public boolean updateGameState(Game game) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(gameFileName));
            String response = new String();
            for (String line; (line = br.readLine()) != null; response += line);
            br.close();

            ArrayList<Game> games = (ArrayList<Game>) encoder.Decode(response, new TypeToken<ArrayList<Game>>(){}.getType());

            if (games == null) {
                games = new ArrayList<Game>(0);
            }

            for (int i = 0; i < games.size(); ++i) {
                if (games.get(i).getID().equals(game.getID())) {
                    games.set(i, game);
                    i = games.size() + 1;
                }
            }

            String gamesJSON = encoder.Encode(games);

            BufferedWriter bw = new BufferedWriter(new FileWriter(gameFileName));
            bw.write(gamesJSON);
            bw.close();

            return true;

        } catch (Exception e) {
            System.out.println("Exception updating game in FileGameDAO");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            return false;
        }
    }

    @Override
    public boolean updateCommandsForGame(Game game, List<Command> commands) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(commandFileName));
            String response = new String();
            for (String line; (line = br.readLine()) != null; response += line);
            br.close();


            ArrayList<AbstractMap.SimpleEntry<String, ArrayList<Command>>> commandList = (ArrayList<AbstractMap.SimpleEntry<String, ArrayList<Command>>>) encoder.Decode(response, new TypeToken<ArrayList<AbstractMap.SimpleEntry<String, ArrayList<Command>>>>(){}.getType());

            if (commandList == null) {
                commandList = new ArrayList<AbstractMap.SimpleEntry<String, ArrayList<Command>>>(0);
            }

            boolean found = false;
            for (int i = 0; i < commandList.size(); ++i) {
                if (commandList.get(i).getKey().equals(game.getID())) {
                    ArrayList<Command> newCommands = new ArrayList<>(commands);
                    commandList.set(i, new AbstractMap.SimpleEntry<String, ArrayList<Command>>(game.getID(), newCommands));
                    found = true;
                    i = commandList.size() + 1;
                }
            }

            if (found == false) {
                ArrayList<Command> newCommands = new ArrayList<>(commands);
                commandList.add(new AbstractMap.SimpleEntry<String, ArrayList<Command>>(game.getID(), newCommands));
            }

            String commandsJSON = encoder.Encode(commandList);

            BufferedWriter bw = new BufferedWriter(new FileWriter(commandFileName));
            bw.write(commandsJSON);
            bw.close();

            return true;

        } catch (Exception e) {
            System.out.println("Exception updating game in FileGameDAO");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            return false;
        }
    }

    @Override
    public boolean clearGames() {
        try {
            BufferedWriter gameBW = new BufferedWriter(new FileWriter(gameFileName));
            gameBW.write("");
            gameBW.close();

            BufferedWriter commandBW = new BufferedWriter(new FileWriter(commandFileName));
            commandBW.write("");
            commandBW.close();

            return true;
        }
        catch (Exception e) {
            System.out.println("Exception clearing games in FileGameDAO");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            return false;
        }
    }
}