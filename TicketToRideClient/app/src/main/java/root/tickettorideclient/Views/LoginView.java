package root.tickettorideclient.Views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import root.tickettorideclient.Callbacks.ILoginCallback;
import root.tickettorideclient.Presenters.ILoginView;
import root.tickettorideclient.Presenters.LoginPresenter;
import root.tickettorideclient.R;


/**
 * Created by Massiel on 5/12/2018.
 */

public class LoginView extends Fragment implements ILoginView {
    private EditText serverHostInput;
    private EditText serverPortInput;
    private EditText usernameInput;
    private EditText passwordInput;

    private Button signInButton;
    private Button registerButton;

    private String username;
    private String password;
    private String serverHost;
    private String serverPort;

    private ILoginPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new LoginPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        setUpButtons(v);
        setUpInputs(v);
        signInButton.setEnabled(false);
        registerButton.setEnabled(false);
        return v;
    }

    public void setUpButtons(View v){
        signInButton = (Button) v.findViewById(R.id.signInButton);
        signInButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                presenter.login(username, password, serverHost, serverPort);
            }
        });

        registerButton = (Button) v.findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                presenter.register(username, password, serverHost, serverPort);
            }
        });
    }

    public void setUpInputs(View v){
        setUpServerHostInput(v);
        setUpServerPortInput(v);
        setUpUsernameInput(v);
        setUpPasswordInput(v);
        setUpPasswordInput(v);

    }

    public void setUpServerHostInput(View v){
        serverHostInput = (EditText) v.findViewById(R.id.serverHostInput);
        serverHostInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                serverHost = editable.toString();
                checkButtons();
            }
        });
    }

    public void setUpServerPortInput(View v){
        serverPortInput = (EditText) v.findViewById(R.id.serverPortInput);
        serverPortInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                serverPort = editable.toString();
                checkButtons();
            }
        });
    }

    public void setUpUsernameInput(View v){
        usernameInput = (EditText) v.findViewById(R.id.usernameInput);
        usernameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                username = editable.toString();
                checkButtons();
            }
        });
    }

    public void setUpPasswordInput(View v) {
        passwordInput = (EditText) v.findViewById(R.id.passwordInput);
        passwordInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                password = editable.toString();
                checkButtons();
            }
        });
    }

    public void checkButtons(){
        if(allFieldsFull()){
            signInButton.setEnabled(true);
            registerButton.setEnabled(true);
        }

        else{
            signInButton.setEnabled(false);
            registerButton.setEnabled(false);
        }

    }

    public boolean allFieldsFull(){

        if(serverHost == null || serverPort == null ||
                username == null || password == null){
//            System.out.println("Fields for sign in are not full");
            return false;
        }

        else if(serverHost.equals("") || serverPort.equals("") ||
                username.equals("") || password.equals("")){
//            System.out.println("Fields for sign in are not full");
            return false;
        }

        return true;
    }

    @Override
    public void disableLoginButton() {
        signInButton.setEnabled(false);
    }

    @Override
    public void enableLoginButton() {
        signInButton.setEnabled(true);
    }

    @Override
    public void disableRegisterButton() {
        registerButton.setEnabled(false);
    }

    @Override
    public void enableRegisterButton() {
        registerButton.setEnabled(true);
    }

    @Override
    public void popToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void switchToGamesView() {
        ((ILoginCallback) getActivity()).onLoginSuccess();
    }
}
