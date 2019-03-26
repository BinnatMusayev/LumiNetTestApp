package practice.com.luminettestapp.Presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import practice.com.luminettestapp.Model.User;
import practice.com.luminettestapp.SplashScreen;
import practice.com.luminettestapp.View.MainActivity;

public class LoginPresenter {

    User user;
    LoginView loginView;
    SharedPreferences.Editor sharedPreferencesEditor;

    public LoginPresenter(LoginView loginView){
        this.loginView = loginView;

        user = new User();
        sharedPreferencesEditor = loginView.getPreferences().edit();

    }

    public void setUser(String username, String password){
        user.setUser(username);
        user.setPass(password);
    }

    public void login(){
        if(user.getUser().equals("user") && user.getPass().equals("pass")){
            loginView.clearErrorMessage();

            //save data to cache
            sharedPreferencesEditor.putBoolean("userLoggedIn", true);
            sharedPreferencesEditor.putString("username", user.getUser());
            sharedPreferencesEditor.putString("password", user.getPass());
            sharedPreferencesEditor.apply();

            //open next activity
            Intent intent = new Intent(loginView.getContext(), MainActivity.class);
            intent.putExtra("username", user.getUser());
            loginView.getContext().startActivity(intent);
        }else{
            loginView.showErrorMessage();
        }
    }


    public interface LoginView{

        void showErrorMessage();
        void clearErrorMessage();
        Context getContext();
        SharedPreferences getPreferences();

    }
}
