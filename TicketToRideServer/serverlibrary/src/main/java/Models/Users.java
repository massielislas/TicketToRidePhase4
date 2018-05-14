package Models;

import java.util.List;

/**
 * Created by Lance on 5/14/2018.
 */

public class Users {
    private List<User> Users;

    boolean alreadyExists(User toCheck)
    {
        if (Users.contains(toCheck))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}