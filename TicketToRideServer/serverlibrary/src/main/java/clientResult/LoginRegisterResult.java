package clientResult;

/**
 * Created by Lance on 5/14/2018.
 */

public class LoginRegisterResult extends Result
{
    public LoginRegisterResult() {

    }

    public LoginRegisterResult(boolean success)
    {
        super(success, null);
    }

    public LoginRegisterResult(boolean success, String message)
    {
        super(success, message);
    }
}
