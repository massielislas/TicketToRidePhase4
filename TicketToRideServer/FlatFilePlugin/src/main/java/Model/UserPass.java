package Model;

/**
 * Created by Topper on 5/14/2018.
 */

/**
 * Created by Master_Chief on 5/14/2018.
 */

public class UserPass {
    private final int MAXLENGTH = 40;
    private String nameOrPassword;

    public UserPass (String nameOrPassword) {
        this.nameOrPassword = nameOrPassword;
    }

    public String getNameOrPassword()
    {
        return nameOrPassword;
    }

    public void setNameOrPassword(String nameOrPassword)
    {
        this.nameOrPassword = nameOrPassword;
    }

    public boolean verify() {
        if (nameOrPassword == null) {
            return false;
        }
        if (nameOrPassword.length() > MAXLENGTH) {
            return false;
        }
        return true;
    }

    @Override
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
    }

    @Override
    public int hashCode() {
        return nameOrPassword.hashCode();
    }
}