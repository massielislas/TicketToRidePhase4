package root.tickettorideclient.Presenters;

/**
 * Created by madeleineaydelotte on 5/14/18.
 */

public interface ILoginView {
    public void disableLoginButton(); //used?
    public void enableLoginButton(); //used?
    public void disableRegisterButton(); //used?
    public void enableRegisterButton(); //used?

    public void popToast(String message);
    public void switchToGamesView();
}