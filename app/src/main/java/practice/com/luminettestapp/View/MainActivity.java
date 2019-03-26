package practice.com.luminettestapp.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import practice.com.luminettestapp.Presenter.MainPresenter;
import practice.com.luminettestapp.R;

public class MainActivity extends AppCompatActivity implements MainPresenter.MainView {

    private MainPresenter presenter;
    private TextView userName;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter(this);
        userName = (TextView) findViewById(R.id.usernameTv);
        linearLayout = (LinearLayout) findViewById(R.id.progressContainer);

        userName.setText(getIntent().getStringExtra("username") + "!");

    }

    public void downloadData(View v){
        linearLayout.setVisibility(View.VISIBLE);
    }
}
