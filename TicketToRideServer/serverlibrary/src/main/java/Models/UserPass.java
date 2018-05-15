package Models;

/**
 * Created by Topper on 5/14/2018.
 */

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
}