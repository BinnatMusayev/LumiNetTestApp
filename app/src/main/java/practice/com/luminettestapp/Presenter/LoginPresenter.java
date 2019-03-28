package practice.com.luminettestapp.Presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import practice.com.luminettestapp.Model.User;
import practice.com.luminettestapp.SplashScreen;
import practice.com.luminettestapp.View.MainActivity;

public class LoginPresenter {
    private final String LOGIN_URL = "http://dev.brandbank.luminet.eu/login";

    User user;
    LoginView loginView;
    SharedPreferences.Editor sharedPreferencesEditor;
    RequestQueue requestQueue;

    public LoginPresenter(LoginView loginView){
        this.loginView = loginView;

        user = new User();
        sharedPreferencesEditor = loginView.getPreferences().edit();

        requestQueue = Volley.newRequestQueue(loginView.getContext());
    }

    public void setUser(String username, String password){
        user.setUser(username);
        user.setPass(password);
    }

    void loginWithWebService(){
        JSONObject jsonBodyObject = null;
        try {
            jsonBodyObject = new JSONObject();

            jsonBodyObject.put("user", loginView.getUsername());
            jsonBodyObject.put("pass", loginView.getPassword());
        }catch (JSONException e){

        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, LOGIN_URL, jsonBodyObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Boolean userAuth = response.getBoolean("userAuth");

                            if (userAuth){
                                loginView.showErrorMessage("SUCCESS");
                            }else {
                                loginView.showErrorMessage("FAILURE");
                            }

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );

        requestQueue.add(jsonObjectRequest);
    }

    public void login(){

        if(user.getUser().equals("luminet") && user.getPass().equals("test")){
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
        void showErrorMessage(String message);
        void clearErrorMessage();
        Context getContext();
        SharedPreferences getPreferences();

        String getUsername();
        String getPassword();

    }
}
