package practice.com.luminettestapp.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import practice.com.luminettestapp.Presenter.MainPresenter;
import practice.com.luminettestapp.R;

public class MainActivity extends AppCompatActivity implements MainPresenter.MainView {

    private MainPresenter presenter;
    private TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter(this);
        userName = (TextView) findViewById(R.id.usernameTv);

        userName.setText(getIntent().getStringExtra("username") + "!");


    }
}
