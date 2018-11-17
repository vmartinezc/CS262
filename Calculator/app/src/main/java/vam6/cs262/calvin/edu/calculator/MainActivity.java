package vam6.cs262.calvin.edu.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    //global  variables
    TextView result;
    Spinner operator;
    TextView value1;
    TextView value2;

    /* onCreate()
    @param: savedInstanceState
    initializes the main activity
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);     //super calls the parent class
        setContentView(R.layout.activity_main); //loads the layout
        operator = (Spinner) findViewById(R.id.spinner);
        value1 = (TextView) findViewById(R.id.value);
        value2 = (TextView) findViewById(R.id.value1);
        result = (TextView) findViewById(R.id.textView3);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Integer v1 = Integer.valueOf(value1.getText().toString()); //first number given by the user
                    Integer v2 = Integer.valueOf(value2.getText().toString()); //second number given by the user

                    if (operator.getSelectedItem().toString().equals("+")) {         //addition
                        Log.e("Value of Answer", String.valueOf(v1 + v2));
                        result.setText(String.valueOf(v1 + v2));
                    } else if (operator.getSelectedItem().toString().equals("-")) { //subtraction
                        Log.e("Result", String.valueOf(v1 - v2));
                        result.setText(String.valueOf(v1 - v2));
                    } else if (operator.getSelectedItem().toString().equals("*")) { //multiplication
                        Log.e("Result", String.valueOf(v1 * v2));
                        result.setText(String.valueOf(v1 * v2));
                    } else if (operator.getSelectedItem().toString().equals("/")) {  //division
                        Log.e("Result", String.valueOf(v1 / v2));
                        result.setText(String.valueOf(v1 / v2));
                    }
                } catch (Exception e) { //Toast message if the user inputs invalid values
                    Toast.makeText(getApplicationContext(), "Invalid Inputs!", Toast.LENGTH_LONG).show();


                }
            }
        });


    }
}
