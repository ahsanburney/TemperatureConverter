package com.ahsanburney.temperatureconverter;

import android.annotation.TargetApi;
import android.content.Context;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.id.button1;

public class TempMainActivity extends AppCompatActivity {


    private TextView history;
    private EditText editTemp;
    private EditText editResultTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        final EditText editTemp = (EditText) findViewById(R.id.editText);
        final EditText editResultTemp = (EditText) findViewById(R.id.editText2);
        history = (TextView) findViewById(R.id.textView4);
        history.setMovementMethod(new ScrollingMovementMethod());
        Button buttonConvert = (Button) findViewById(R.id.button1);

        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            @TargetApi(24)
            public void onClick(View v) {

                switch (v.getId()){
                    case R.id.button1:
                        RadioButton fahrenheitButton = (RadioButton) findViewById(R.id.radioButton0);
                        RadioButton celciusButton = (RadioButton) findViewById(R.id.radioButton1);
                        String historyText = history.getText().toString();
                        if(editTemp.getText().length()==0){
                            int duration = Toast.LENGTH_SHORT;
                            Context context = getApplicationContext();
                            Toast.makeText(context,"Please Enter Some Value", duration).show();
                            return;
                        }

                        double inputValue=Double.parseDouble(editTemp.getText().toString());
                        double inputValueResult=Double.parseDouble(editTemp.getText().toString());
                        if(fahrenheitButton.isChecked()){
                            DecimalFormat oneDigit = new DecimalFormat("#.#");
                            editResultTemp.setText(String.valueOf(oneDigit.format(convertFahrenheitToCelsius(inputValue))));
                            history.setText("F to C:  "+ inputValueResult  + " = " +(String.valueOf(oneDigit.format(convertFahrenheitToCelsius(inputValue))))+"\n" + historyText);
                            history.setMovementMethod(new ScrollingMovementMethod());
                            celciusButton.setChecked(false);
                            fahrenheitButton.setChecked(true);
                        }else
                        {
                            DecimalFormat oneDigit = new DecimalFormat("#.#");
                            editResultTemp.setText(String.valueOf(oneDigit.format(convertCelsiusToFahrenheit(inputValue))));
                            history.setText("C to F:  "+ inputValueResult  + " = " +(String.valueOf(oneDigit.format(convertCelsiusToFahrenheit(inputValue))))+"\n" + historyText);
                            fahrenheitButton.setChecked(false);
                            celciusButton.setChecked(true);
                        }
                        break;

                }

            }

               /* double temp = Double.valueOf(editTemp.getText().toString());

                double fahrenheit =(temp - 32.0) * 5.0/9.0;
                editResultTemp.setText(String.valueOf(fahrenheit)); */
            });
        };

    private double convertFahrenheitToCelsius(double fahrenheit){
        return((fahrenheit - 32.0)* 5.0/9.0);
    }

    private double convertCelsiusToFahrenheit(double celsius){
        return((celsius * 9.0/5.0)+32.0);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("HISTORY", history.getText().toString());

        // Call super last
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        // Call super first
        super.onRestoreInstanceState(savedInstanceState);
        history.setText(savedInstanceState.getString("HISTORY"));

    }

}


