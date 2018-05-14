package clientResult;

public class LoginRegisterResult implements Result
{
    boolean result = false;
    String message = "not set";

    public boolean isValid(){return result;}

    public void setResult(boolean result){this.result = result;}

    public String getMessage(){return message;}

    public void setMessage(String toSet){message = toSet;}
}
