package DAOs;

/**
 * Created by madeleineaydelotte on 6/14/18.
 */

public interface IDAOFactory {
    public IGameDAO createGameDAO();
    public IUserDAO createUserDAO();
}
