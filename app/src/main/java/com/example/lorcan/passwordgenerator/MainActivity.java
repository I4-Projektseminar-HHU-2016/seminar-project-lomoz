package com.example.lorcan.passwordgenerator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private static SeekBar seekBar;
    private static TextView textViewSeekBarValue;
    private static int seekBarValue;

    private static boolean uppercaseOn = false;
    private static boolean lowercaseOn = false;
    private static boolean numbersOn = false;
    private static boolean specialsOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //commentary

        setSeekBar();
    }


    // Von YouTube aus "Android Tutorial for Beginners 19 #SeekBar" von "ProgrammingKnowledge"
    public void setSeekBar() {

        seekBar = (SeekBar)findViewById(R.id.seekBar);
        textViewSeekBarValue = (TextView)findViewById(R.id.textViewSeekBar);

        seekBar.setProgress(4);

        seekBarValue = 8;

        //Test
        System.out.println(seekBarValue);

        textViewSeekBarValue.setText(String.valueOf(seekBarValue));

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {



            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                seekBarValue = (progress + 4);
                textViewSeekBarValue.setText(String.valueOf(seekBarValue));

                //Test
                System.out.println(seekBarValue);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    public void buttonClicked(View v) {

        TextView tv = (TextView) findViewById(R.id.textView2);
        tv.setText("Password with length: " + seekBarValue);
        Toast.makeText(MainActivity.this, "New Password generated", Toast.LENGTH_LONG).show();
    }

    public void kleinbuchstabenChecked(View v) {
        //code to check if this checkbox is checked!
        CheckBox checkBox = (CheckBox) v;
        if (checkBox.isChecked()) {

            lowercaseOn = true;

            System.out.println("Lowercase enthalten: ");
            System.out.println(lowercaseOn);
        }
    }

    public void großbuchstabenChecked(View v) {
        //code to check if this checkbox is checked!
        CheckBox checkBox = (CheckBox) v;
        if (checkBox.isChecked()) {

            uppercaseOn = true;

            System.out.println("Uppercase enthalten: ");
            System.out.println(uppercaseOn);
        }
    }

    public void numbersChecked(View v) {
        //code to check if this checkbox is checked!
        CheckBox checkBox = (CheckBox) v;
        if (checkBox.isChecked()) {

            numbersOn = true;

            System.out.println("Numbers enthalten: ");
            System.out.println(numbersOn);
        }
    }

    public void specialsChecked(View v) {
        //code to check if this checkbox is checked!
        CheckBox checkBox = (CheckBox) v;
        if (checkBox.isChecked()) {

            specialsOn = true;

            System.out.println("Specials enthalten: ");
            System.out.println(specialsOn);
        }
    }



}
