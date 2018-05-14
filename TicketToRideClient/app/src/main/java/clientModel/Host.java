package clientModel;

public class Host {
    String data;

    public Host(String string)
    {
        data = string;
    }

    public boolean verify()
    {
        char[] dataArray = data.toCharArray();
        for (char c: dataArray)
        {
            if ((c != '.') && (!Character.isDigit(c)) && (!Character.isLetter(c))) return false;
        }
        return true;
    }
}
