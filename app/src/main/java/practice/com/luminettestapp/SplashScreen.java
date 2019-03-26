package practice.com.luminettestapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import practice.com.luminettestapp.View.LoginActivity;
import practice.com.luminettestapp.View.MainActivity;

public class SplashScreen extends AppCompatActivity {
    private static int TIMEOUT = 2500;
    public static String USER_PREFERENCE = "user";

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //checking if there is logged in user
        sharedPreferences = getSharedPreferences(USER_PREFERENCE, MODE_PRIVATE);
        final Intent intent = new Intent();
        Class nextActivity;
        if(sharedPreferences.getBoolean("userLoggedIn", false)){
            nextActivity = MainActivity.class;
            String str = sharedPreferences.getString("username", "");
            intent.putExtra("username", str);
        }else{
            nextActivity = LoginActivity.class;
        }

        intent.setClass(SplashScreen.this, nextActivity);

        //change to another screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
            }
        }, TIMEOUT);
    }
}
