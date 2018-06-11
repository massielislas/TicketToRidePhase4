package Model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Topper on 6/11/2018.
 */
public class UserPassTest {
    @Test
    public void verify() throws Exception {
        UserPass test = new UserPass("good");
        assertEquals(test.verify(), true);
    }
    @Test
    public void verify2() throws Exception {
        UserPass test = new UserPass("!good");
        assertEquals(test.verify(),false);
    }

    @Test
    public void verify3() throws Exception {
        UserPass test = new UserPass("0123456789012345678901234567890123456789111991911919191991919191919191911911");
        assertEquals (test.verify(), false);
    }

    @Test
    public void verify4() throws Exception {
        UserPass test = new UserPass("0123456789012345678901234567890123456789");
        assertEquals (test.verify(), true);
    }

    @Test
    public void verify5() throws Exception {
        UserPass test = new UserPass("");
        assertEquals (test.verify(), true);
    }

    @Test
    public void verify6() throws Exception {
        UserPass test = new UserPass(null);
        assertEquals (test.verify(), false);
    }

    @Test
    public void equals() throws Exception {
        UserPass test1 = new UserPass("notNull");
        UserPass test2 = new UserPass("notNull");
        assertEquals(test1, test2);

    }

    @Test
    public void hashCodeTest() throws Exception {

    }

}