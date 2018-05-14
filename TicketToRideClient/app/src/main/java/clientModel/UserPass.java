package clientModel;

/**
 * Created by Lance on 5/14/2018.
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
        char[] dataArray = nameOrPassword.toCharArray();

        for (char c: dataArray)
        {
            if ((!Character.isDigit(c)) && (!Character.isLetter(c))) return false;
        }
        return true;
    }
}
