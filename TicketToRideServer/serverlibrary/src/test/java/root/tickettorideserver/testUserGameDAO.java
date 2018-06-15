package root.tickettorideserver;

import org.junit.Test;

import java.util.List;

import DataPersistence.FileUserDAO;
import Model.User;
import Model.UserPass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by madeleineaydelotte on 6/15/18.
 */

public class testUserGameDAO {

    @Test
    public void test1 () {
        FileUserDAO dao = new FileUserDAO("serverlibrary/src/main/java/DataPersistence/testUserfile.txt");

        UserPass username = new UserPass("fakeUser");
        UserPass password = new UserPass( "faker");
        User user = new User(username, password);

        boolean result = dao.addUser(user);
        assertEquals(result, true);

    }

    @Test
    public void test2 () {
        FileUserDAO dao = new FileUserDAO("serverlibrary/src/main/java/DataPersistence/testUserfile.txt");

        List<User> users = dao.loadUsers();

        for (int i = 0; i < users.size(); ++i) {
            System.out.println(users.get(i).getUserName().getNameOrPassword());
        }

        assertEquals(users.size(), 1);
    }

    @Test
    public void test3 () {
        FileUserDAO dao = new FileUserDAO("serverlibrary/src/main/java/DataPersistence/emptyfile.txt");

        List<User> users = dao.loadUsers();

        assertNull(users);
    }
}
