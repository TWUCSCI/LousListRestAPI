package cs4720.cs.virginia.edu.louslistrestapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 Notes: This is an example of using Retrofit library to pull data asynchronously from a REST JSON API
 based upon the department requested in the EditText. Helper code is provided in the LousListAPI
 classes and the Section class.
 Try different department ID such as CS, AAS etc. to see results

 Modified by Gratch on 3/21/20.

 **/

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "/view/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void callAPI(View view) {
        // create a service and a functioning REST client
        LousListAPIInterface apiService =
                LousListAPIClient.getClient().create(LousListAPIInterface.class);

        // get department information from user input
        EditText mnemonic = (EditText)findViewById(R.id.editText);
        String mnemonicSearch = mnemonic.getText().toString();

        // Execute the request asynchronously, download the data with the provided department
        // information and save in a List of of Section. Then display all courses information.
        Call<List<Section>> call = apiService.sectionList(mnemonicSearch);
        call.enqueue(new Callback<List<Section>>() {
            @Override
            public void onResponse(Call<List<Section>> call, Response<List<Section>> response) {
                List<Section> sections = response.body();
                String courseDisplay = "";
                for(Section s : sections) {
                    Log.d("LousList", "Received: " + s);
                    courseDisplay += s + "\n";
                }
                TextView display = (TextView)findViewById(R.id.textview);
                display.setText(courseDisplay);
            }

            @Override
            public void onFailure(Call<List<Section>> call, Throwable t) {
                // Log error here since request failed
                Log.e("LousList", t.toString());
            }
        });

    }
}
