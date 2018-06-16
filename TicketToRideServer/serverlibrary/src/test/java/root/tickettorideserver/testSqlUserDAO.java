package root.tickettorideserver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import DataPersistence.SQLiteUserDAO;
import Model.User;
import Model.UserPass;

import static org.junit.Assert.assertTrue;

/**
 * Created by Massiel on 6/15/2018.
 */

public class testSqlUserDAO {
    SQLiteUserDAO dao = new SQLiteUserDAO();
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
