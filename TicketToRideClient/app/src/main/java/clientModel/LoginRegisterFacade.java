package clientModel;

import clientResult.LoginRegisterResult;

/**
 * Created by zachgormley on 5/13/18.
 */

public class LoginRegisterFacade
{
    TicketToRideProxy proxy;
    UserData userData = UserData.getUserData();

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
            LoginRegisterResult result = new LoginRegisterResult(false);
            //result.setSuccess(false);
            result.setMessage("Invalid input!");
            return result;
        }
    }

    private LoginRegisterResult login(UserPass username, UserPass password, Host host, Port port)
    {
        LoginRegisterResult result = proxy.loginUser(username, password, host, port);
        if (result.isSuccess())
        {
            userData.setUsername(username);
            userData.setHost(host);
            userData.setPort(port);
        }
        return result;
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
            LoginRegisterResult result = new LoginRegisterResult(false);
            result.setMessage("Invalid input!");
            return result;
        }
    }

    private LoginRegisterResult register(UserPass username, UserPass password, Host host, Port port)
    {
        LoginRegisterResult result = proxy.registerUser(username, password, host, port);
        if (result.isSuccess())
        {
            userData.setUsername(username);
            userData.setHost(host);
            userData.setPort(port);
        }
        return result;
    }

}
