package Model;

/**
 * Created by Topper on 5/15/2018.
 */

class Host {
    String data;

    Host(String string)
    {
        data = string;
    }

    boolean verify()
    {
        char[] dataArray = data.toCharArray();
        for (char c: dataArray)
        {
            if ((c != '.') && (!Character.isDigit(c)) && (!Character.isLetter(c))) return false;
        }
        return true;
    }
}
