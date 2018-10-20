package vam6.cs262.calvin.edu.homework02;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.ListView;
import android.os.Bundle;
import android.support.v4.content.Loader;


import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<String>{

        private EditText input;
        private Button fetch_button;
        private ListView player_list;


        @Override
        protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = (EditText) findViewById(R.id.num_input);
        input.setTransformationMethod(null);
        player_list = (ListView) findViewById(R.id.player_list);
        fetch_button = (Button) findViewById(R.id.fetch_button);

        if (getSupportLoaderManager().getLoader(0) != null) {
            getSupportLoaderManager().initLoader(0, null, this);
        }
    }


        public void searchPlayers (View view){
        String queryString = input.getText().toString();
        //   new FetchPlayer(player).execute(queryString);
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected() && queryString.length() != 0) {
            Bundle queryBundle = new Bundle();
            queryBundle.putString("input", queryString);
            getSupportLoaderManager().restartLoader(0, queryBundle, this);

        } else {
            displayToast("Check connection and try again, boi");
        }
    }


        @Override
        public Loader<String> onCreateLoader ( int i, Bundle bundle){
            return new FetchPlayer(this, bundle.getString("input"));

    }

        @Override
        public void onLoadFinished (@NonNull Loader <String> loader, String s){
        try {

            JSONObject jsonObject = new JSONObject(s);
            String id;
            String name;
            String email;
            List<String> player_array_list = new ArrayList<String>();

            if (jsonObject.has("items")) {
                JSONArray itemsArray = jsonObject.getJSONArray("items");
                //Iterate through the results
                for (int i = 0; i < itemsArray.length(); i++) {
                    JSONObject player = itemsArray.getJSONObject(i); //Get the current item


                    try {
                        id = player.getString("id");
                    } catch (Exception e) {
                        id = "no ID";
                    }

                    try {
                        name = player.getString("name");
                    } catch (Exception e) {
                        name = "no name";
                    }

                    try {
                        email = player.getString("emailAddress");
                    } catch (Exception e) {
                        email = "no email";
                    }
                    player_array_list.add(id + ", " + name + ", " + email);
                }
            } else {
                try {
                    id = jsonObject.getString("id");
                } catch (Exception e) {
                    id = "no ID";
                }

                try {
                    name = jsonObject.getString("name");
                } catch (Exception e) {
                    name = "no name";
                }

                try {
                    email = jsonObject.getString("emailAddress");
                } catch (Exception e) {
                    email = "no email";
                }

                player_array_list.add(id + ", " + name + ", " + email);

            }

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_1,
                    player_array_list);

            player_list.setAdapter(arrayAdapter);
        } catch (Exception e) {

            displayToast("Nonexistent ID");
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }


    private void displayToast (String s){
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();

    }
    }

