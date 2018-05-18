package root.tickettorideclient.Presenters;

import Model.LoginRegisterFacade;
import Results.LoginRegisterResult;
import root.tickettorideclient.Views.ILoginPresenter;


/**
 * Created by madeleineaydelotte on 5/14/18.
 */

public class LoginPresenter implements ILoginPresenter {

    private ILoginView view = null;
    private LoginRegisterFacade facade = null;

    public LoginPresenter (ILoginView view) {
        this.view = view;
        this.facade = new LoginRegisterFacade();
    }

    public void register(String username, String password, String host, String port) {
        //TODO: verify

        //call register method
        LoginRegisterResult result = facade.verifyRegister(username, password, host, port);

        //if unsuccessful,
        //pop toast with error message
        if (!result.isSuccess()) {
            view.popErrorToast(result.getMessage());
        }

        //if successful,
        //switch views
        if (result.isSuccess()) {
            view.switchToGamesView();
        }

        return;
    }

    public void login(String username, String password, String host, String port) {
        //TODO: verify

        //call login method
        LoginRegisterResult result = facade.verifyLogin(username, password, host, port);


        //if unsuccessful,
        //pop toast with error message
        if (!result.isSuccess()) {
            view.popErrorToast(result.getMessage());
        }


        //if successful,
        //switch views
        if (result.isSuccess()) {
            view.switchToGamesView();
        }
    }
}
