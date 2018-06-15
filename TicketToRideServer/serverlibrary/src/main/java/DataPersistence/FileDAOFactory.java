package DataPersistence;

/**
 * Created by madeleineaydelotte on 6/14/18.
 */

public class FileDAOFactory implements IDAOFactory {

    private String commandFileName;
    private String gameFileName;
    private String userFileName;

    public FileDAOFactory () {
        this.commandFileName = "/serverlibrary/src/main/java/DataPersistence/commands.txt";
        this.gameFileName = "/serverlibrary/src/main/java/DataPersistence/games.txt";
        this.userFileName = "/serverlibrary/src/main/java/DataPersistence/users.txt";
    }

    @Override
    public IGameDAO createGameDAO() {
        return new FileGameDAO(commandFileName, gameFileName);
    }

    @Override
    public IUserDAO createUserDAO() {
        return new FileUserDAO(userFileName);
    }
}
