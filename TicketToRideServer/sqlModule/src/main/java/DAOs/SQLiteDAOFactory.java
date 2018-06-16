package DAOs;

/**
 * Created by madeleineaydelotte on 6/14/18.
 */

public class SQLiteDAOFactory implements IDAOFactory {
    @Override
    public IGameDAO createGameDAO() {
        return  new SQLiteGameDAO();
    }

    @Override
    public IUserDAO createUserDAO() {
        return new SQLiteUserDAO();
    }
}
