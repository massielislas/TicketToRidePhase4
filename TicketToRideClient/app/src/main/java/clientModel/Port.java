package clientModel;

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
