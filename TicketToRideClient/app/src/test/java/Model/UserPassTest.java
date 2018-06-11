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
        assert (test.verify());
    }
    @Test
    public void verify2() throws Exception {
        UserPass test = new UserPass("!good");
        assert (!test.verify());
    }

    @Test
    public void verify3() throws Exception {
        UserPass test = new UserPass("01234567890123456789012345678901234567891");
        assert (!test.verify());
    }

    @Test
    public void equals() throws Exception {

    }

    @Test
    public void hashCodeTest() throws Exception {

    }

}