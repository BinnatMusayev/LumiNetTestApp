package practice.com.luminettestapp.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import practice.com.luminettestapp.Model.User;
import practice.com.luminettestapp.Presenter.LoginPresenter;
import practice.com.luminettestapp.R;
import practice.com.luminettestapp.SplashScreen;

public class LoginActivity extends AppCompatActivity implements LoginPresenter.LoginView{

    LoginPresenter loginPresenter;
    EditText loginField, passField;
    TextView errorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginPresenter = new LoginPresenter(this);

        loginField = (EditText) findViewById(R.id.loginField);
        passField = (EditText) findViewById(R.id.passField);
        errorMessage = (TextView) findViewById(R.id.loginErrorMessage);

    }

    public void login(View v){
        loginPresenter.setUser(loginField.getText().toString(), passField.getText().toString());
        loginPresenter.login();
    }

    @Override
    public void showErrorMessage() {
        errorMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void clearErrorMessage() {
        errorMessage.setVisibility(View.GONE);
    }

    @Override
    public SharedPreferences getPreferences() {
        return this.getSharedPreferences(SplashScreen.USER_PREFERENCE, MODE_PRIVATE);

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public String getUsername() {
        return loginField.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return passField.getText().toString().trim();
    }

    @Override
    public void showErrorMessage(String message) {
        errorMessage.setText(message);
        errorMessage.setVisibility(View.VISIBLE);
    }
}
