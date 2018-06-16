package DataPersistence;


/**
 * Created by madeleineaydelotte on 6/14/18.
 */

public class SQLiteDAOFactory implements IDAOFactory {
    private String commandFileName;
    private String gameFileName;
    private String userFileName;

    private String gameDAOClass;
    private String userDAOClass;

    public SQLiteDAOFactory(){
        this.gameDAOClass = "DataPersistence.SQLiteGameDAO";
        this.userDAOClass = "DataPersistence.SQLiteUserDAO";

    }
    @Override
    public IGameDAO createGameDAO() {
        IGameDAO gameDAO = null;

        try{
            Class klass = Class.forName(gameDAOClass);
            Object o = klass.newInstance();
        } catch (Exception e){
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
        try{
            Class klass = Class.forName(userDAOClass);
            Object o = klass.newInstance();
            userDAO = (IUserDAO) o;
        } catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            System.out.println(e.getStackTrace().toString());
            return null;
        }
        return userDAO;
    }
}
