package Models;

/**
 * Created by Topper on 5/14/2018.
 */

import java.util.Objects;

/**
 * Created by Master_Chief on 5/14/2018.
 */

public class UserPass {
    private final int MAXLENGTH = 40;
    private String nameOrPassword;

    public boolean verify()
    {
        if (nameOrPassword == null)
        {
            return false;
        }
        if (nameOrPassword.length() > MAXLENGTH)
        {
            return false;
        }
        return true;
    }

    @Override
<<<<<<< HEAD
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UserPass compare = (UserPass) obj;

        if (compare == this) {
            return true;
        }

        if (!(nameOrPassword.equals(compare.nameOrPassword))) {
            return false;
        }

        return true;
=======
    public boolean equals(Object o) {
        if(o instanceof UserPass)
        {
            UserPass userPassObj = (UserPass) o;
            if(userPassObj.nameOrPassword.equals(this.nameOrPassword))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return nameOrPassword.hashCode();
>>>>>>> integration
    }
}