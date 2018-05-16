package clientModel;

import clientModel.Command;
import clientModel.Host;
import clientModel.Port;
import clientModel.UserPass;
import clientResult.LoginRegisterResult;

/**
 * Created by zachgormley on 5/13/18.
 */

public class LoginRegisterFacade
{
    TicketToRideProxy proxy;

    public LoginRegisterResult verifyLogin(String strUsername, String strPassword, String strHost, String strPort)
    {
        UserPass username = new UserPass(strUsername);
        UserPass password = new UserPass(strPassword);
        Host host = new Host(strHost);
        Port port = new Port(strPort);

        boolean valid = username.verify() && password.verify() && host.verify() && port.verify();
        if (valid) return login(username, password, host, port);
        else
        {
            LoginRegisterResult result = new LoginRegisterResult();
            result.setResult(false);
            result.setMessage("Invalid input!");
            return result;
        }
    }

    private LoginRegisterResult login(UserPass username, UserPass password, Host host, Port port)
    {
        return proxy.registerUser(username, password, host, port);
    }

    public LoginRegisterResult verifyRegister(String strUsername, String strPassword, String strHost, String strPort)
    {
        UserPass username = new UserPass(strUsername);
        UserPass password = new UserPass(strPassword);
        Host host = new Host(strHost);
        Port port = new Port(strPort);

        boolean valid = username.verify() && password.verify() && host.verify() && port.verify();
        if (valid) return register(username, password, host, port);
        else
        {
            LoginRegisterResult result = new LoginRegisterResult();
            result.setResult(false);
            result.setMessage("Invalid input!");
            return result;
        }
    }

    private LoginRegisterResult register(UserPass username, UserPass password, Host host, Port port)
    {
        return proxy.loginUser(username, password, host, port);
    }

}
