package DataPersistence;

import java.util.List;

import Model.User;

/**
 * Created by madeleineaydelotte on 6/14/18.
 */

public class SQLiteUserDAO implements IUserDAO {
    @Override
    public List<User> loadUsers() {
        return null;
    }

    @Override
    public boolean addUser(User user) {
        return false;
    }
}
