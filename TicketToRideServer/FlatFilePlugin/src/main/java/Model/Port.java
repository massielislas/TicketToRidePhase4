package Model;

/**
 * Created by Topper on 5/15/2018.
 */

class Port {
    String data;

    Port(String string){data = string;}

    boolean verify()
    {
        char[] dataArray = data.toCharArray();
        for (char c: dataArray)
        {
            if ((!Character.isDigit(c)) && (!Character.isLetter(c))) return false;
        }
        return true;
    }
}
