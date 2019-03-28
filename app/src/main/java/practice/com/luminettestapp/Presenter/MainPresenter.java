package practice.com.luminettestapp.Presenter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import practice.com.luminettestapp.Model.User;

public class MainPresenter {
    private final String URL_DATA = "http://dev.brandbank.luminet.eu/product/6155887/dataSheet";
    private final String URL_IMAGE = "http://dev.brandbank.luminet.eu/product/6155887/coverImage";
    private MainView mainView;
    private User user;
    private RequestQueue requestQueue;

    public MainPresenter(MainView mainView){
        this.mainView = mainView;

        user = new User();

        requestQueue = Volley.newRequestQueue(mainView.getContext());
    }

    public void downloadData(){
        mainView.toggleProgessBar();
        //after getting json

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL_DATA, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String desc =  response.getString("description");
                            String bbc =  response.getString("brandbankCode");
                            String vdt =  response.getString("versionDateTime");
                            String ec =  response.getString("eu1169Compliance");
                            String dl =  response.getString("defaultLanguage");

                            JSONArray catArray  = response.getJSONArray("categories");
                            String categories  = "\n";
                            for(int i=0; i< catArray.length(); i++){
                                JSONObject catObj  =catArray.getJSONObject(i);
                                categories = categories + catObj.getString("description") + "("
                                        + catObj.getString("level") + ", "
                                        + catObj.getString("code") + ")\n";
                            }
                            // getting rid of last coma and empty space
//                            categories  = categories.substring(0, categories.length()-2);

                            //set Image
                            Picasso.get().load(URL_IMAGE).into(mainView.getImageView());
                            //showing data layout
                            mainView.setJsonDataNames(desc, bbc, vdt, ec, dl, categories );
                            mainView.toggleProgessBar();
                            mainView.hideDownloadButton();
                            mainView.showDataContainer();
                        }catch(JSONException e){
                            e.printStackTrace();
                            Toast.makeText(mainView.getContext(), "Catch error", Toast.LENGTH_SHORT);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(mainView.getContext(), "ErrorListener", Toast.LENGTH_SHORT);
                        mainView.toggleProgessBar();
                        new AlertDialog.Builder(mainView.getContext())
                                .setTitle("Error Message")
                                .setMessage("Something went wrong while downloading data")
                                .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        downloadData();
                                    }
                                })
                                .setNegativeButton("Cancel", null).show();



                        error.printStackTrace();
                    }
                });

        requestQueue.add(jsonObjectRequest);

    }

    public User getUser(){
        return this.user;
    }

    public interface MainView{
        void toggleProgessBar();
        void showDataContainer();
        void setJsonDataNames(String description, String brandbankCode, String versionDateTime,
                              String eu1169Compliance, String defaultLanguage, String categories);
        Context getContext();
        void hideDownloadButton();
        ImageView getImageView();
    }


}
