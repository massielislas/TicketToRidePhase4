package Model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HostTest {

    @Test
    public void verify() {

        //Invalid Host test
        Host host = new Host("<5g$asf");
        assertFalse(host.verify());

        //Valid Host Test with decimal
        Host host2 = new Host("10.24.17.118");
        assertTrue(host2.verify());

        //Valid Host Test w/o decimal
        Host host3 = new Host("localhost");
        assertTrue(host3.verify());

        //Invalid Host Test
        Host host4 = new Host("__032..fsdf");
        assertFalse(host4.verify());

    }

}