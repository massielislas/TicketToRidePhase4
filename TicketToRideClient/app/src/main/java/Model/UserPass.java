package Model;

import android.annotation.TargetApi;

import java.util.Objects;

/**
 * Created by Lance on 5/14/2018.
 */

public class UserPass {
    private final int MAXLENGTH = 40;
    private String nameOrPassword;

    public UserPass(String string)
    {
        nameOrPassword = string;
    }

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
    @TargetApi(19)
    public int hashCode() {
        return Objects.hash(nameOrPassword);
    }

    public String getNameOrPassword () { return nameOrPassword; }
}
