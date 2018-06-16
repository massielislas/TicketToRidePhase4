package DataPersistence;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Created by madeleineaydelotte on 6/14/18.
 */

public class FileDAOFactory implements IDAOFactory {

    private String commandFileName;
    private String gameFileName;
    private String userFileName;

    private String fileGameDAOClass;
    private String fileUserDAOClass;

    public FileDAOFactory () {
        this.commandFileName = "/FlatFilePlugin/src/main/java/DataPersistence/commands.txt";
        this.gameFileName = "/FlatFilePlugin/src/main/java/DataPersistence/games.txt";
        this.userFileName = "/FlatFilePlugin/src/main/java/DataPersistence/users.txt";

        this.fileGameDAOClass = "DataPersistence.FileGameDAO";
        this.fileUserDAOClass = "DataPersistence.FileUserDAO";

    }

    @Override
    public IGameDAO createGameDAO() {
        return new FileGameDAO(commandFileName, gameFileName);
    }

    @Override
    public IUserDAO createUserDAO() {

        IUserDAO userDAO = null;

        try {
            Class klass = Class.forName(fileUserDAOClass);
            Object o = klass.newInstance();


          //  Constructor<?> constructor = klass.getConstructor(null);
          //  Object o = constructor.newInstance(new Object[] {null});

            userDAO = (IUserDAO) o;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            System.out.println(e.getStackTrace().toString());
            return null;
        }
        return userDAO;
    }
}
