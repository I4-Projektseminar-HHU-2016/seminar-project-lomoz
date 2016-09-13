package com.example.lorcan.passwordgenerator;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import static com.example.lorcan.passwordgenerator.FillArrayList.*;

//import static com.example.lorcan.passwordgenerator.fillArrayList.fillPasswordArrayListChars;

public class MainActivity extends AppCompatActivity {

    private static SeekBar seekBar;
    private static TextView textViewSeekBarValue;
    private static int seekBarValue;

    private static Button buttonCopyPassword;
    private static TextView textViewPassword;

    private static boolean uppercaseOn = false;
    private static boolean lowercaseOn = false;
    private static boolean numbersOn = false;
    private static boolean specialsOn = false;

    private static boolean atLeastOneCheckboxChecked = false;

    private static ArrayList<String> passwordChars = new ArrayList<>();

    private static String trialPassword = "";

    private static boolean hasUppercase;
    private static boolean hasLowercase;
    private static boolean hasNumber;
    private static boolean hasSpecial;

    private static boolean isValidPassword;

    private static String finalPassword = "";

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

    public void buttonGeneratePasswordClicked(View v) {

        textViewPassword= (TextView) findViewById(R.id.textViewPassword);
        buttonCopyPassword = (Button) findViewById(R.id.buttonCopyPassword);

        atLeastOneCheckboxChecked = uppercaseOn || lowercaseOn || numbersOn || specialsOn;

        if (!atLeastOneCheckboxChecked) {

            Toast.makeText(MainActivity.this, "Choose at least one CheckBox!", Toast.LENGTH_LONG).show();
            System.out.println("At least one CheckBox checked:  " + atLeastOneCheckboxChecked + "\n");

            textViewPassword.setText("Password");
            buttonCopyPassword.setVisibility(View.INVISIBLE);
        }
        else {

            System.out.println("At least one CheckBox checked:  " + atLeastOneCheckboxChecked + "\n");


            Toast.makeText(MainActivity.this, "New Password generated", Toast.LENGTH_LONG).show();

            /*
             * From Class File "FillArrayList"
             */
            fillPasswordArrayListChars(passwordChars, uppercaseOn, lowercaseOn, numbersOn, specialsOn);

            textViewPassword.setText(finalPassword);
            buttonCopyPassword.setVisibility(View.VISIBLE);
        }
    }

    public void buttonCopyPasswordClicked(View v) {

        System.out.println("Button to Copy Password clicked!");

        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("copy password", finalPassword);
        clipboardManager.setPrimaryClip(clipData);

        Toast.makeText(MainActivity.this, "Password copied!", Toast.LENGTH_LONG).show();
    }

    public void uppercaseChecked(View v) {
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


    public void lowercaseChecked(View v) {
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
     * Die Funktion erstellt ein "vorab Passwort".
     * An Hand der zuvor gewählten Länge wird zufällig
     * ein Element der ArrayList mit allen Chars erzeugt.
     *
     * Ob wirklich alle ausgewählten Paramenter mindestens
     * ein Mal im generierten Passwort vorkommt, wird in der
     * Funktion "checkPassword" geprüft.
     * Diese wird am Ende aufgerufen.
     */


    public static void makePassword() {

        try {

            trialPassword = "";

            for (int i = 0; i < seekBarValue; i++) {
                Random randomizer = new Random();
                String random = passwordChars.get(randomizer.nextInt(passwordChars.size()));
                trialPassword = trialPassword + random;
            }

            System.out.println("Generated password: " + trialPassword + "\n");

            checkPassword();

        } catch (IllegalArgumentException e) {

            e.printStackTrace();
            System.out.println("Not possible, choose at least one value!");
        }
    }

    /*
     * Gibt "true" zurück, wenn in "trialPassword"
     * mindestens ein Uppercase enthalten ist.
     */
    public static boolean checkUppercase() {

        hasUppercase = !trialPassword.equals(trialPassword.toLowerCase());

        System.out.println("Uppercase contained: " + hasUppercase + "\n");

        return hasUppercase;
    }

    /*
     * Gibt "true" zurück, wenn in "trialPassword"
     * mindestens ein Lowercase enthalten ist.
     */
    public static boolean checkLowercase() {

        hasLowercase = !trialPassword.equals(trialPassword.toUpperCase());

        System.out.println("Lowercase contained: " + hasLowercase + "\n");

        return hasLowercase;
    }

    /*
     * Gibt "true" zurück, wenn in "trialPassword"
     * mindestens eine Zahl enthalten ist.
     */
    public static boolean checkNumber() {

        hasNumber = trialPassword.matches(".*\\d+.*");

        System.out.println("Number contained: " + hasNumber + "\n");

        return hasNumber;
    }

    /*
     * Gibt "true" zurück, wenn in "trialPassword"
     * mindestens ein Sonderzeichen enthalten ist.
     */
    public static boolean checkSpecial() {

        hasSpecial = !trialPassword.matches("[A-Za-z0-9]*");

        System.out.println("Special contained: " + hasSpecial + "\n");

        return hasSpecial;
    }

    /*
     * Überprüft alle möglichen Kombinationen der Checkboxen.
     * Wenn alle Parameter überinstimmen wird die boolische Varibale "isValidPassword" auf "true" gesetzt".
     * Andernfalls wird "isValidPassword" auf "false" gesetzt.
     *
     * Am Ende wird überprüft ob "isValidPassword" den Wert "true" hat.
     * Trifft dies zu, so wird "trailPassword" an "finalPassword" übergeben.
     * Wenn nicht, dann wird "makePassword" wiederholt.
     *
     * Dies führt dazu, dass so lange ein Passwort erzeugt wird, bis eins existiert,
     * welches allen gewählten Paramentern entspricht.
     *
     * Das fertige Passwort wird dann in der entsprechenden TextView angezeigt.
     */
    public static void checkPassword() {

        checkUppercase();
        checkLowercase();
        checkNumber();
        checkSpecial();

        // Alle vier Werte auf "true"
        if (uppercaseOn && lowercaseOn & numbersOn && specialsOn) {

            if (hasUppercase && hasLowercase && hasNumber && hasSpecial) {
                isValidPassword = true;
            }
            else {
                isValidPassword = false;
            }
        }

        // Uppercase auf "false", sonst alles "true"
        else if (!uppercaseOn && lowercaseOn & numbersOn && specialsOn) {

            if (!hasUppercase && hasLowercase && hasNumber && hasSpecial) {
                isValidPassword = true;
            }
            else {
                isValidPassword = false;
            }
        }

        // Lowercase auf "false", sonst alles auf "true"
        else if (uppercaseOn && !lowercaseOn & numbersOn && specialsOn) {

            if (hasUppercase && !hasLowercase && hasNumber && hasSpecial) {
                isValidPassword = true;
            }
            else {
                isValidPassword = false;
            }
        }

        // Numbers auf "false", sonst alles auf "true"
        else if (uppercaseOn && lowercaseOn & !numbersOn && specialsOn) {

            if (hasUppercase && hasLowercase && !hasNumber && hasSpecial) {
                isValidPassword = true;
            }
            else {
                isValidPassword = false;
            }
        }

        // Specials auf "false", sonst alles auf "true"
        else if (uppercaseOn && lowercaseOn & numbersOn && !specialsOn) {

            if (hasUppercase && hasLowercase && hasNumber && !hasSpecial) {
                isValidPassword = true;
            }
            else {
                isValidPassword = false;
            }
        }

        // Uppercase & Lowercase auf "false", Numbers & Specials auf "true"
        else if (!uppercaseOn && !lowercaseOn & numbersOn && specialsOn) {

            if (!hasUppercase && !hasLowercase && hasNumber && hasSpecial) {
                isValidPassword = true;
            }
            else {
                isValidPassword = false;
            }
        }

        // Uppercase & Numbers auf "false", Lowercase & Specials auf "true"
        else if (!uppercaseOn && lowercaseOn & !numbersOn && specialsOn) {

            if (!hasUppercase && hasLowercase && !hasNumber && hasSpecial) {
                isValidPassword = true;
            }
            else {
                isValidPassword = false;
            }
        }

        // Uppercase & Specials auf "false", Lowercase & Numbers auf "true"
        else if (!uppercaseOn && lowercaseOn & numbersOn && !specialsOn) {

            if (!hasUppercase && hasLowercase && hasNumber && !hasSpecial) {
                isValidPassword = true;
            }
            else {
                isValidPassword = false;
            }
        }

        // Lowercase & Numbers auf "false", Uppercase & Specials auf "true"
        else if (uppercaseOn && !lowercaseOn & !numbersOn && specialsOn) {

            if (hasUppercase && !hasLowercase && !hasNumber && hasSpecial) {
                isValidPassword = true;
            }
            else {
                isValidPassword = false;
            }
        }

        // Lowercase & Specials auf "false", Uppercase & Numbers auf "true"
        else if (uppercaseOn && !lowercaseOn & numbersOn && !specialsOn) {

            if (hasUppercase && !hasLowercase && hasNumber && !hasSpecial) {
                isValidPassword = true;
            }
            else {
                isValidPassword = false;
            }
        }

        // Numbers & Specials auf "false", Uppercase & Lowercase auf "true"
        else if (uppercaseOn && lowercaseOn & !numbersOn && !specialsOn) {

            if (hasUppercase && hasLowercase && !hasNumber && !hasSpecial) {
                isValidPassword = true;
            }
            else {
                isValidPassword = false;
            }
        }

        // Uppercase auf "true", sonst alles "false"
        else if (uppercaseOn && !lowercaseOn & !numbersOn && !specialsOn) {

            if (hasUppercase && !hasLowercase && !hasNumber && !hasSpecial) {
                isValidPassword = true;
            }
            else {
                isValidPassword = false;
            }
        }

        // Lowercase auf "true", sonst alles "false"
        else if (!uppercaseOn && lowercaseOn & !numbersOn && !specialsOn) {

            if (!hasUppercase && hasLowercase && !hasNumber && !hasSpecial) {
                isValidPassword = true;
            }
            else {
                isValidPassword = false;
            }
        }

        // Numbers auf "true", sonst alles "false"
        else if (!uppercaseOn && !lowercaseOn & numbersOn && !specialsOn) {

            if (!hasUppercase && !hasLowercase && hasNumber && !hasSpecial) {
                isValidPassword = true;
            }
            else {
                isValidPassword = false;
            }
        }

        // Specials auf "true", sonst alles "false"
        else if (!uppercaseOn && !lowercaseOn & !numbersOn && specialsOn) {

            if (!hasUppercase && !hasLowercase && !hasNumber && hasSpecial) {
                isValidPassword = true;
            }
            else {
                isValidPassword = false;
            }
        }

        // Check boolean "isValidPassword"
        if (isValidPassword) {
            System.out.println("Alles gut\n");
            System.out.println("------------------------------\n");
            finalPassword = trialPassword;
            System.out.println("Final password: " + finalPassword);
        }
        else {
            makePassword();
        }
    }
}
