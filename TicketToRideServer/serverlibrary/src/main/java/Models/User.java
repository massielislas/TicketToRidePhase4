package Models;

import java.util.UUID;

/**
 * Created by Lance on 5/14/2018.
 */

public class User {

    private String ID;
    private UserPass userName;
    private UserPass password;

    public User(UserPass newName, UserPass newPass) {
        this.userName = newName;
        this.password = newPass;
        ID = UUID.randomUUID().toString();
    }

    @Override
    public boolean equals(Object obj)
    {
        return true;
    }

}