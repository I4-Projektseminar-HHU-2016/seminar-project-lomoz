package com.example.lorcan.passwordgenerator;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

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

        setButtonWidth();

        setSeekBar();
    }

    /*
     * Options Menu Tutorial from:
     * "Android Studio Tutorials - 32 : Options Menu in Android (Menu's)"
     * by "rams android"
     * Code Lines: 64 - 103
     * Link https://www.youtube.com/watch?v=empirRnFzwM
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.item_option1_settings:

                startActivity(new Intent(getApplicationContext(), ActivitySettings.class));

                break;

            case R.id.item_option2_help:

                startActivity(new Intent(getApplicationContext(), ActivityHelp.class));

                break;

            case R.id.item_option3_feedback:

                startActivity(new Intent(getApplicationContext(), ActivityFeedback.class));

                break;

            case R.id.item_option4_about:

                startActivity(new Intent(getApplicationContext(), ActivityAbout.class));

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /*
    public void buttonGoToGeneratePassword(View v) {

        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
    */

    public void buttonGoToEasyPassword(View v) {

        startActivity(new Intent(getApplicationContext(), ActivityEasyRemember.class));
    }

    public void buttonGoToCheckSafety(View v) {

        startActivity(new Intent(getApplicationContext(), ActivitySafetyCheck.class));
    }

    public void setButtonWidth() {

        try {

            Button buttonPasswordGenerator = (Button) findViewById(R.id.buttonPWGenerator);
            Button buttonEasyPassword = (Button) findViewById(R.id.buttonEasyPassword);
            Button buttonCheckSafety = (Button) findViewById(R.id.buttonCheckSafety);

            int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
            System.out.println("Screen width: " + screenWidth);

            int buttonWidth = (screenWidth / 3);

            System.out.println("Button width: " + buttonWidth);

            buttonPasswordGenerator.setWidth(buttonWidth);
            buttonEasyPassword.setWidth(buttonWidth);
            buttonCheckSafety.setWidth(buttonWidth);

        } catch (NullPointerException e) {

            e.printStackTrace();
            System.out.println("Caught NullPointerException!!");
        }
    }

    /*
     * From YouTube: "Android Tutorial for Beginners 19 #SeekBar" by "ProgrammingKnowledge"
     * Link: https://www.youtube.com/watch?v=l5FrTkGoeX8
     * Line 153 to 177
     * Changes due to my use
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

    public void uppercaseChecked(View v) {
        //code to check if this checkbox is checked!
        CheckBox checkBox = (CheckBox) v;

        if (checkBox.isChecked()) {

            uppercaseOn = true;
        }
        else {

            uppercaseOn = false;
        }

        System.out.println("Uppercase contained: " + uppercaseOn);

    }


    public void lowercaseChecked(View v) {
        //code to check if this checkbox is checked!
        CheckBox checkBox = (CheckBox) v;
        if (checkBox.isChecked()) {

            lowercaseOn = true;
        }
        else {

            lowercaseOn = false;
        }

        System.out.println("Lowercase contained: " + lowercaseOn);

    }


    public void numbersChecked(View v) {
        //code to check if this checkbox is checked!
        CheckBox checkBox = (CheckBox) v;
        if (checkBox.isChecked()) {

            numbersOn = true;
        }
        else {

            numbersOn = false;
        }

        System.out.println("Numbers contained: " + numbersOn);

    }


    public void specialsChecked(View v) {
        //code to check if this checkbox is checked!
        CheckBox checkBox = (CheckBox) v;
        if (checkBox.isChecked()) {

            specialsOn = true;
        }
        else {

            specialsOn = false;
        }

        System.out.println("Specials contained: " + specialsOn);

    }

    public void buttonGeneratePasswordClicked(View v) {

        TextView textViewPassword = (TextView) findViewById(R.id.textViewPassword);
        Button buttonCopyPassword = (Button) findViewById(R.id.buttonCopyPassword);

        boolean atLeastOneCheckboxChecked = uppercaseOn || lowercaseOn || numbersOn || specialsOn;

        if (!atLeastOneCheckboxChecked) {

            Toast.makeText(MainActivity.this, "Choose at least one CheckBox!", Toast.LENGTH_SHORT).show();
            textViewPassword.setText("Password");
            buttonCopyPassword.setVisibility(View.INVISIBLE);
        }
        else {

            /*
             * From Class File "FillArrayList"
             */
            FillArrayList var = new FillArrayList();
            var.fillPasswordArrayListChars(passwordChars, uppercaseOn, lowercaseOn, numbersOn, specialsOn);

            textViewPassword.setText(finalPassword);
            buttonCopyPassword.setVisibility(View.VISIBLE);

            Toast.makeText(MainActivity.this, "New Password generated", Toast.LENGTH_SHORT).show();
        }

        System.out.println("At least one CheckBox checked:  " + atLeastOneCheckboxChecked + "\n");

    }

    public void buttonCopyPasswordClicked(View v) {

        System.out.println("Button to Copy Password clicked!");

        /*
         * From Stackoverflow: How to Copy Text to Clip Board in Android?
         * Link: http://stackoverflow.com/questions/19253786/how-to-copy-text-to-clip-board-in-android
         * Line 302 - 304
         */

        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("copy password", finalPassword);
        clipboardManager.setPrimaryClip(clipData);

        Toast.makeText(MainActivity.this, "Password copied!", Toast.LENGTH_SHORT).show();
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
