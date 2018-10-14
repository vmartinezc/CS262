package vam6.cs262.calvin.edu.lab6;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import vam6.cs262.calvin.edu.lab04final.R;
import vam6.cs262.calvin.edu.lab06.SettingsActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        PreferenceManager.setDefaultValues(this, R.xml.pref_general, false);
        PreferenceManager.setDefaultValues(this, R.xml.pref_notification, false);
        PreferenceManager.setDefaultValues(this, R.xml.pref_data_sync, false);

        SharedPreferences sharedPref =
                PreferenceManager.getDefaultSharedPreferences(this);
        String marketPref = sharedPref.getString("sync_frequency", "-1");
        Toast.makeText(this, marketPref, Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_order:
                displayToast(getString(R.string.action_order_message));
                return true;
            case R.id.action_status:
                displayToast(getString(R.string.action_status_message));
                return true;
            case R.id.action_favorites:
                displayToast(getString(R.string.action_favorites_message));
                return true;
            case R.id.action_contact:
                displayToast(getString(R.string.action_contact_message));
                return true;

            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



        /**
         * Shows a message that the donut image was clicked.
         */
        public void showDonutOrder (View view){
            showFoodOrder(getString(R.string.donut_order_message));
        }

        /**
         * Shows a message that the ice cream sandwich image was clicked.
         */
        public void showIceCreamOrder (View view){
            showFoodOrder(getString(R.string.ice_cream_order_message));
        }

        /**
         * Shows a message that the froyo image was clicked.
         */
        public void showFroyoOrder (View view){
            showFoodOrder(getString(R.string.froyo_order_message));
        }

        /**
         * Displays a toast message for the food order
         * and starts the OrderActivity activity.
         * @param message   Message to display.
         */
        public void showFoodOrder (String message){
            Toast.makeText(getApplicationContext(), message,
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, OrderActivity.class);
            startActivity(intent);
        }

        public void displayToast (String message){
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }


    }

