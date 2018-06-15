package root.tickettorideserver;

import org.junit.Test;
import static org.junit.Assert.*;

import DataPersistence.FileUserDAO;

/**
 * Created by madeleineaydelotte on 6/14/18.
 */

public class testFileGameDAO {

    @Test
    public void test () {
        FileUserDAO dao = new FileUserDAO("serverlibrary/src/main/java/DataPersistence/testfile.txt");
        boolean result = dao.testWrite("hello");
        assertEquals(result, true);
    }

}
