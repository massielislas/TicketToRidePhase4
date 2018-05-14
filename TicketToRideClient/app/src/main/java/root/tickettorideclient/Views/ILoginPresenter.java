package root.tickettorideclient.Views;

/**
 * Created by madeleineaydelotte on 5/14/18.
 */

public interface ILoginPresenter {
    public void register(String username, String password, String host, String port);
    public void login(String username, String password, String host, String port);
}