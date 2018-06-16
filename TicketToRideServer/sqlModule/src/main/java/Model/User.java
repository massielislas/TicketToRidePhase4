package Model;

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

    public UserPass getUserName() {
        return userName;
    }

    public UserPass getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof User)
        {
            User userObj = (User) obj;
            if(userObj.ID.equals(this.ID) && userObj.password.equals(this.password)
                    && userObj.userName.equals(this.userName)) {
                return true;
            }
        }
        return false;
    }

}