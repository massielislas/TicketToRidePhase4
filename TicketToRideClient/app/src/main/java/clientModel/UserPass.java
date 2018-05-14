package clientModel;

public class UserPass
{
    String data;

    public UserPass(String string){data = string;}

    public boolean verify()
    {
        char[] dataArray = data.toCharArray();
        for (char c: dataArray)
        {
            if ((!Character.isDigit(c)) && (!Character.isLetter(c))) return false;
        }
        return true;
    }
}
