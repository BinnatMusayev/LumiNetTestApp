package practice.com.luminettestapp.View;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import practice.com.luminettestapp.Presenter.MainPresenter;
import practice.com.luminettestapp.R;

public class MainActivity extends AppCompatActivity implements MainPresenter.MainView {

    private MainPresenter presenter;
    private TextView userName;
    private LinearLayout linearLayout;
    private Button downloadButton;
    //Data containers
    private LinearLayout dataContainer;
    private ImageView serviceImage;
    private TextView description, brandbankCode, versionDateTime, eu1169Compliance, defaultLanguage, categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter(this);
        userName = (TextView) findViewById(R.id.usernameTv);
        linearLayout = (LinearLayout) findViewById(R.id.progressContainer);
        downloadButton = (Button) findViewById(R.id.downloadBtn);

        userName.setText(getIntent().getStringExtra("username") + "!");


        //Data containers
        dataContainer = (LinearLayout) findViewById(R.id.dataContainer);
        serviceImage = (ImageView) findViewById(R.id.serviceImage);
        description = (TextView) findViewById(R.id.description);
        brandbankCode = (TextView) findViewById(R.id.brandbankCode);
        versionDateTime = (TextView) findViewById(R.id.versionDateTime);
        eu1169Compliance = (TextView) findViewById(R.id.eu1169Compliance);
        defaultLanguage = (TextView) findViewById(R.id.defaultLanguage);
        categories = (TextView) findViewById(R.id.categories);

    }

    public void downloadData(View v){
        presenter.downloadData();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void toggleProgessBar() {
        if (linearLayout.getVisibility() == View.VISIBLE){
            linearLayout.setVisibility(View.GONE);
        }else{
            linearLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showDataContainer() {
        dataContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void setJsonDataNames(String description, String brandbankCode, String versionDateTime, String eu1169Compliance, String defaultLanguage, String categories) {
        this.description.setText(this.description.getText().toString() + description);
        this.brandbankCode.setText(this.brandbankCode.getText().toString() + brandbankCode);
        this.versionDateTime.setText(this.versionDateTime.getText().toString() + versionDateTime);
        this.eu1169Compliance.setText(this.eu1169Compliance.getText().toString() + eu1169Compliance);
        this.defaultLanguage.setText(this.defaultLanguage.getText().toString() + defaultLanguage);
        this.categories.setText(this.categories.getText().toString() + categories);
    }

    @Override
    public void hideDownloadButton() {
        downloadButton.setVisibility(View.GONE);
    }

    @Override
    public ImageView getImageView() {
        return this.serviceImage;
    }
}
