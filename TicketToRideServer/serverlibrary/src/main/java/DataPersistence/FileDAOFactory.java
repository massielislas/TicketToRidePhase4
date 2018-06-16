package DataPersistence;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Created by madeleineaydelotte on 6/14/18.
 */

public class FileDAOFactory implements IDAOFactory {

    private String fileGameDAOClass;
    private String fileUserDAOClass;

    public FileDAOFactory () {
        this.fileGameDAOClass = "DataPersistence.FileGameDAO";
        this.fileUserDAOClass = "DataPersistence.FileUserDAO";
    }

    @Override
    public IGameDAO createGameDAO() {
        IGameDAO gameDAO = null;
        try {
            Class klass = Class.forName(fileGameDAOClass);
            Object o = klass.newInstance();

            gameDAO = (IGameDAO) o;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            System.out.println(e.getStackTrace().toString());
            return null;
        }
        return gameDAO;
    }

    @Override
    public IUserDAO createUserDAO() {
        IUserDAO userDAO = null;
        try {
            Class klass = Class.forName(fileUserDAOClass);
            Object o = klass.newInstance();

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
