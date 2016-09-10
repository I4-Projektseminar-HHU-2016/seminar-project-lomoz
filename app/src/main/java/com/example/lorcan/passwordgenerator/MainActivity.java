package com.example.lorcan.passwordgenerator;

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

public class MainActivity extends AppCompatActivity {


    private static SeekBar seekBar;
    private static TextView textViewSeekBarValue;
    private static int seekBarValue;

    private static Button buttonCopyPassword;

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

        TextView tv = (TextView) findViewById(R.id.textViewPassword);
        //tv.setText("Password with length: " + seekBarValue);
        Toast.makeText(MainActivity.this, "New Password generated", Toast.LENGTH_LONG).show();

        fillPasswordArrayListChars();

        tv.setText(finalPassword);

        buttonCopyPassword = (Button) findViewById(R.id.buttonCopyPassword);
        buttonCopyPassword.setVisibility(View.VISIBLE);
    }

    public void buttonCopyPasswordClicked(View v) {

        System.out.println("Button to Copy Password clicked!");
        Toast.makeText(MainActivity.this, "Password copied!", Toast.LENGTH_LONG).show();


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

        makePassword();
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
        if (uppercaseOn == true && lowercaseOn == true & numbersOn == true && specialsOn == true) {

            if (hasUppercase == true && hasLowercase == true && hasNumber == true && hasSpecial == true) {
                isValidPassword = true;
            }
            else {
                isValidPassword = false;
            }
        }

        // Uppercase auf "false", sonst alles "true"
        else if (uppercaseOn == false && lowercaseOn == true & numbersOn == true && specialsOn == true) {

            if (hasUppercase == false && hasLowercase == true && hasNumber == true && hasSpecial == true) {
                isValidPassword = true;
            }
            else {
                isValidPassword = false;
            }
        }

        // Lowercase auf "false", sonst alles auf "true"
        else if (uppercaseOn == true && lowercaseOn == false & numbersOn == true && specialsOn == true) {

            if (hasUppercase == true && hasLowercase == false && hasNumber == true && hasSpecial == true) {
                isValidPassword = true;
            }
            else {
                isValidPassword = false;
            }
        }

        // Numbers auf "false", sonst alles auf "true"
        else if (uppercaseOn == true && lowercaseOn == true & numbersOn == false && specialsOn == true) {

            if (hasUppercase == true && hasLowercase == true && hasNumber == false && hasSpecial == true) {
                isValidPassword = true;
            }
            else {
                isValidPassword = false;
            }
        }

        // Specials auf "false", sonst alles auf "true"
        if (uppercaseOn == true && lowercaseOn == true & numbersOn == true && specialsOn == false) {

            if (hasUppercase == true && hasLowercase == true && hasNumber == true && hasSpecial == false) {
                isValidPassword = true;
            }
            else {
                isValidPassword = false;
            }
        }

        // Uppercase & Lowercase auf "false", Numbers & Specials auf "true"
        else if (uppercaseOn == false && lowercaseOn == false & numbersOn == true && specialsOn == true) {

            if (hasUppercase == false && hasLowercase == false && hasNumber == true && hasSpecial == true) {
                isValidPassword = true;
            }
            else {
                isValidPassword = false;
            }
        }

        // Uppercase & Numbers auf "false", Lowercase & Specials auf "true"
        else if (uppercaseOn == false && lowercaseOn == true & numbersOn == false && specialsOn == true) {

            if (hasUppercase == false && hasLowercase == true && hasNumber == false && hasSpecial == true) {
                isValidPassword = true;
            }
            else {
                isValidPassword = false;
            }
        }

        // Uppercase & Specials auf "false", Lowercase & Numbers auf "true"
        else if (uppercaseOn == false && lowercaseOn == true & numbersOn == true && specialsOn == false) {

            if (hasUppercase == false && hasLowercase == true && hasNumber == true && hasSpecial == false) {
                isValidPassword = true;
            }
            else {
                isValidPassword = false;
            }
        }

        // Lowercase & Numbers auf "false", Uppercase & Specials auf "true"
        else if (uppercaseOn == true && lowercaseOn == false & numbersOn == false && specialsOn == true) {

            if (hasUppercase == true && hasLowercase == false && hasNumber == false && hasSpecial == true) {
                isValidPassword = true;
            }
            else {
                isValidPassword = false;
            }
        }

        // Lowercase & Specials auf "false", Uppercase & Numbers auf "true"
        else if (uppercaseOn == true && lowercaseOn == false & numbersOn == true && specialsOn == false) {

            if (hasUppercase == true && hasLowercase == false && hasNumber == true && hasSpecial == false) {
                isValidPassword = true;
            }
            else {
                isValidPassword = false;
            }
        }

        // Numbers & Specials auf "false", Uppercase & Lowercase auf "true"
        else if (uppercaseOn == true && lowercaseOn == true & numbersOn == false && specialsOn == false) {

            if (hasUppercase == true && hasLowercase == true && hasNumber == false && hasSpecial == false) {
                isValidPassword = true;
            }
            else {
                isValidPassword = false;
            }
        }

        // Uppercase auf "true", sonst alles "false"
        else if (uppercaseOn == true && lowercaseOn == false & numbersOn == false && specialsOn == false) {

            if (hasUppercase == true && hasLowercase == false && hasNumber == false && hasSpecial == false) {
                isValidPassword = true;
            }
            else {
                isValidPassword = false;
            }
        }

        // Lowercase auf "true", sonst alles "false"
        else if (uppercaseOn == false && lowercaseOn == true & numbersOn == false && specialsOn == false) {

            if (hasUppercase == false && hasLowercase == true && hasNumber == false && hasSpecial == false) {
                isValidPassword = true;
            }
            else {
                isValidPassword = false;
            }
        }

        // Numbers auf "true", sonst alles "false"
        else if (uppercaseOn == false && lowercaseOn == false & numbersOn == true && specialsOn == false) {

            if (hasUppercase == false && hasLowercase == false && hasNumber == true && hasSpecial == false) {
                isValidPassword = true;
            }
            else {
                isValidPassword = false;
            }
        }

        // Specials auf "true", sonst alles "false"
        else if (uppercaseOn == false && lowercaseOn == false & numbersOn == false && specialsOn == true) {

            if (hasUppercase == false && hasLowercase == false && hasNumber == false && hasSpecial == true) {
                isValidPassword = true;
            }
            else {
                isValidPassword = false;
            }
        }

        // Check boolean "isValidPassword"
        if (isValidPassword == true) {
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
