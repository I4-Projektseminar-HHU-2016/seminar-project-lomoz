package com.example.lorcan.passwordgenerator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private static SeekBar seekBar;
    private static TextView textViewSeekBarValue;
    private static int seekBarValue;

    private static boolean uppercaseOn = false;
    private static boolean lowercaseOn = false;
    private static boolean numbersOn = false;
    private static boolean specialsOn = false;

    private static String[] uppercaseStrings = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private static String[] lowercaseStrings = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    private static String[] numbersStrings = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private static String[] specialsStrings = {"!", "?", "@", "(", ")", "{", "}", "[", "]", "/", "=", "~", "$", "%", "&", "#", "*", "-"};

    private static ArrayList<String> passwordChars = new ArrayList<>();

    private static int passwordCharsLength;

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

        fillPasswordArrayListChars();
    }

    public void kleinbuchstabenChecked(View v) {
        //code to check if this checkbox is checked!
        CheckBox checkBox = (CheckBox) v;
        if (checkBox.isChecked()) {

            lowercaseOn = true;

            System.out.println("Lowercase enthalten: " + lowercaseOn);
        }
        else {

            lowercaseOn = false;

            System.out.println("Lowercase enthalten: " + lowercaseOn);
        }
    }

    public void großbuchstabenChecked(View v) {
        //code to check if this checkbox is checked!
        CheckBox checkBox = (CheckBox) v;
        if (checkBox.isChecked()) {

            uppercaseOn = true;

            System.out.println("Uppercase enthalten: " + uppercaseOn);
        }
        else {

            uppercaseOn = false;

            System.out.println("Uppercase enthalten: " + uppercaseOn);
        }
    }

    public void numbersChecked(View v) {
        //code to check if this checkbox is checked!
        CheckBox checkBox = (CheckBox) v;
        if (checkBox.isChecked()) {

            numbersOn = true;

            System.out.println("Numbers enthalten: " + numbersOn);
        }
        else {

            numbersOn = false;

            System.out.println("Numbers enthalten: " + numbersOn);

        }
    }

    public void specialsChecked(View v) {
        //code to check if this checkbox is checked!
        CheckBox checkBox = (CheckBox) v;
        if (checkBox.isChecked()) {

            specialsOn = true;

            System.out.println("Specials enthalten: " + specialsOn);
        }
        else {

            specialsOn = false;

            System.out.println("Specials enthalten: " + specialsOn);

        }
    }


    /*
     * Funktion, die eine ArrayList je nach gewählten Einstellungen
     * der Checkboxes mit den entsprechenden Chars füllt.
     */
    public static void fillPasswordArrayListChars() {

        passwordChars.clear();

        if (uppercaseOn == true) {

            for (int i = 0; i < uppercaseStrings.length; i++) {

                passwordChars.add(uppercaseStrings[i]);
            }
        }

        if (lowercaseOn == true) {

            for (int i = 0; i < lowercaseStrings.length; i++) {

                passwordChars.add(lowercaseStrings[i]);
            }
        }

        if (numbersOn == true) {

            for (int i = 0; i < numbersStrings.length; i++) {

                passwordChars.add(numbersStrings[i]);
            }
        }

        if (specialsOn == true) {

            for (int i = 0; i < specialsStrings.length; i++) {

                passwordChars.add(specialsStrings[i]);
            }
        }

        System.out.println("\nArrayList mit allen ausgewählten Elementen: " + passwordChars + "\n");

        passwordCharsLength = passwordChars.size();

        System.out.println("Länge der ArrayList mit allen ausgewählten Elementen: " + passwordCharsLength + "\n");
    }

}
