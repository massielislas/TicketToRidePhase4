package DataPersistence;

import java.sql.Connection;
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
        System.out.println("Adding user to database...");

        boolean success = false;
        String sqlStatement = "";
        Connection connection = null;

        sqlStatement += "INSERT INTO User(username, user)" + '\n';
        sqlStatement += "Values('";

        return false;
    }
}
