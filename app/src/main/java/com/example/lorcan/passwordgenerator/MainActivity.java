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

public class MainActivity extends AppCompatActivity {

    private static TextView textViewSeekBarValue;
    private static int seekBarValue;

    private static boolean uppercaseOn = false;
    private static boolean lowercaseOn = false;
    private static boolean numbersOn = false;
    private static boolean specialsOn = false;

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

    /*
     * From YouTube: "Android Tutorial for Beginners 19 #SeekBar" by "ProgrammingKnowledge"
     */
    public void setSeekBar() {

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
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

        TextView textViewPassword = (TextView) findViewById(R.id.textViewPassword);
        Button buttonCopyPassword = (Button) findViewById(R.id.buttonCopyPassword);

        boolean atLeastOneCheckboxChecked = uppercaseOn || lowercaseOn || numbersOn || specialsOn;

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
            FillArrayList var = new FillArrayList();
            var.fillPasswordArrayListChars(passwordChars, uppercaseOn, lowercaseOn, numbersOn, specialsOn);

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
     * The method generates, with the parameters chosen
     * by the user, a trail password.
     * It accords to the length of the SeekBar and chooses
     * randomly from the ArrayList with all Chars.
     *
     * If every chosen parameter is at least once in the
     * generated password will be checked by the method
     * "checkPassword", which is called at the end.
     */
    public void makePassword() {

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
     * Returns "true", if "trialPassword"
     * contains at least one uppercase.
     */
    public static boolean checkUppercase() {

        hasUppercase = !trialPassword.equals(trialPassword.toLowerCase());

        System.out.println("Uppercase contained: " + hasUppercase + "\n");

        return hasUppercase;
    }

    /*
     * Returns "true", if "trialPassword"
     * contains at least one lowercase.
     */
    public static boolean checkLowercase() {

        hasLowercase = !trialPassword.equals(trialPassword.toUpperCase());

        System.out.println("Lowercase contained: " + hasLowercase + "\n");

        return hasLowercase;
    }

    /*
     * Returns "true", if "trialPassword"
     * contains at least one number.
     */
    public static boolean checkNumber() {

        hasNumber = trialPassword.matches(".*\\d+.*");

        System.out.println("Number contained: " + hasNumber + "\n");

        return hasNumber;
    }

    /*
     * Returns "true", if "trialPassword"
     * contains at least one special character.
     */
    public static boolean checkSpecial() {

        hasSpecial = !trialPassword.matches("[A-Za-z0-9]*");

        System.out.println("Special contained: " + hasSpecial + "\n");

        return hasSpecial;
    }

    /*
     * Checks all possible combinations of the CheckBoxes.
     * If all parameters accord, the boolean variable "isValidPassword" is set "true".
     * Otherwise "isValidPassword" is set "false".
     *
     * At the end the method checks if "isValidPassword" has the value "true".
     * If it applies, "trialPassword" is consigned to "finalPassword".
     * If not, "makePassword" will be repeated.
     *
     * As the result, there will be a new generated password, until one is generated,
     * which fits all chosen parameters.
     *
     * The final password will be displayed in the equivalent TextView.
     */
    public void checkPassword() {

        checkUppercase();
        checkLowercase();
        checkNumber();
        checkSpecial();

        // All four Booleans set "true"
        if (uppercaseOn && lowercaseOn & numbersOn && specialsOn) {

            if (hasUppercase && hasLowercase && hasNumber && hasSpecial) {
                isValidPassword = true;
            }
            else {
                isValidPassword = false;
            }
        }

        // Uppercase set "false", everything else set "true"
        else if (!uppercaseOn && lowercaseOn & numbersOn && specialsOn) {

            if (!hasUppercase && hasLowercase && hasNumber && hasSpecial) {
                isValidPassword = true;
            }
            else {
                isValidPassword = false;
            }
        }

        // Lowercase set "false", everything else set "true"
        else if (uppercaseOn && !lowercaseOn & numbersOn && specialsOn) {

            if (hasUppercase && !hasLowercase && hasNumber && hasSpecial) {
                isValidPassword = true;
            }
            else {
                isValidPassword = false;
            }
        }

        // Numbers set "false", everything else set "true"
        else if (uppercaseOn && lowercaseOn & !numbersOn && specialsOn) {

            if (hasUppercase && hasLowercase && !hasNumber && hasSpecial) {
                isValidPassword = true;
            }
            else {
                isValidPassword = false;
            }
        }

        // Specials set "false", everything else set "true"
        else if (uppercaseOn && lowercaseOn & numbersOn && !specialsOn) {

            if (hasUppercase && hasLowercase && hasNumber && !hasSpecial) {
                isValidPassword = true;
            }
            else {
                isValidPassword = false;
            }
        }

        // Uppercase & Lowercase set "false", Numbers & Specials set "true"
        else if (!uppercaseOn && !lowercaseOn & numbersOn && specialsOn) {

            if (!hasUppercase && !hasLowercase && hasNumber && hasSpecial) {
                isValidPassword = true;
            }
            else {
                isValidPassword = false;
            }
        }

        // Uppercase & Numbers set "false", Lowercase & Specials set "true"
        else if (!uppercaseOn && lowercaseOn & !numbersOn && specialsOn) {

            if (!hasUppercase && hasLowercase && !hasNumber && hasSpecial) {
                isValidPassword = true;
            }
            else {
                isValidPassword = false;
            }
        }

        // Uppercase & Specials set "false", Lowercase & Numbers set "true"
        else if (!uppercaseOn && lowercaseOn & numbersOn && !specialsOn) {

            if (!hasUppercase && hasLowercase && hasNumber && !hasSpecial) {
                isValidPassword = true;
            }
            else {
                isValidPassword = false;
            }
        }

        // Lowercase & Numbers set "false", Uppercase & Specials set "true"
        else if (uppercaseOn && !lowercaseOn & !numbersOn && specialsOn) {

            if (hasUppercase && !hasLowercase && !hasNumber && hasSpecial) {
                isValidPassword = true;
            }
            else {
                isValidPassword = false;
            }
        }

        // Lowercase & Specials set "false", Uppercase & Numbers set "true"
        else if (uppercaseOn && !lowercaseOn & numbersOn && !specialsOn) {

            if (hasUppercase && !hasLowercase && hasNumber && !hasSpecial) {
                isValidPassword = true;
            }
            else {
                isValidPassword = false;
            }
        }

        // Numbers & Specials set "false", Uppercase & Lowercase set "true"
        else if (uppercaseOn && lowercaseOn & !numbersOn && !specialsOn) {

            if (hasUppercase && hasLowercase && !hasNumber && !hasSpecial) {
                isValidPassword = true;
            }
            else {
                isValidPassword = false;
            }
        }

        // Uppercase set "true", everything else "false"
        else if (uppercaseOn && !lowercaseOn & !numbersOn && !specialsOn) {

            if (hasUppercase && !hasLowercase && !hasNumber && !hasSpecial) {
                isValidPassword = true;
            }
            else {
                isValidPassword = false;
            }
        }

        // Lowercase set "true", everything else "false"
        else if (!uppercaseOn && lowercaseOn & !numbersOn && !specialsOn) {

            if (!hasUppercase && hasLowercase && !hasNumber && !hasSpecial) {
                isValidPassword = true;
            }
            else {
                isValidPassword = false;
            }
        }

        // Numbers set "true", everything else "false"
        else if (!uppercaseOn && !lowercaseOn & numbersOn && !specialsOn) {

            if (!hasUppercase && !hasLowercase && hasNumber && !hasSpecial) {
                isValidPassword = true;
            }
            else {
                isValidPassword = false;
            }
        }

        // Specials set "true", everything else set "false"
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
