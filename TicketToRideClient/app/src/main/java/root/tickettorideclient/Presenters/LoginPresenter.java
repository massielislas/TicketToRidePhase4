package root.tickettorideclient.Presenters;

import root.tickettorideclient.Views.ILoginPresenter;


/**
 * Created by madeleineaydelotte on 5/14/18.
 */

public class LoginPresenter implements ILoginPresenter {

    private ILoginView loginView = null;

    public LoginPresenter (ILoginView loginView) {
        this.loginView = loginView;
    }

    public void register(String username, String password, String host, String port) {
        //TODO: write me

        //check input for validity


        //if false,


        //if successful,
        //send off to server

        //if successful,

        //if false,

        return;
    }

    public void login(String username, String password, String host, String port) {
        //TODO: write me
        return;
    }
}
