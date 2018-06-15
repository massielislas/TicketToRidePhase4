package DataPersistence;

import java.util.List;

import Model.User;

/**
 * Created by madeleineaydelotte on 6/14/18.
 */

public interface IUserDAO {
    public List<User> loadUsers();
    public boolean addUser(User user);
}
