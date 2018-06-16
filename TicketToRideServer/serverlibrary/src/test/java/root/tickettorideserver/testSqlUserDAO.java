package root.tickettorideserver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import DataPersistence.IUserDAO;
import DataPersistence.SQLiteDAOFactory;
import Model.User;
import Model.UserPass;

import static org.junit.Assert.assertTrue;

/**
 * Created by Massiel on 6/15/2018.
 */

public class testSqlUserDAO {
    IUserDAO dao = new SQLiteDAOFactory().createUserDAO();
    @Before
    public void clearUsers(){
        dao.clearUsers();
    }

    @Test
    public void insertUser(){
        UserPass username = new UserPass("massiel");
        UserPass password = new UserPass("password");
        User user = new User(username, password);


        assertTrue(dao.addUser(user));
    }

    @Test
    public void getUser(){
        UserPass username = new UserPass("massiel");
        UserPass password = new UserPass("password");
        User user = new User(username, password);


        assertTrue(dao.addUser(user));

        List<User> users = dao.loadUsers();

        assertTrue(users.contains(user));

    }

    @After
    public void afterClear(){
        dao.clearUsers();
    }

}
