package com.example.lorcan.passwordgenerator;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ActivitySafetyCheck extends AppCompatActivity {

    private static String password;

    private static boolean hasUppercase = false;
    private static boolean hasLowercase = false;
    private static boolean hasNumber = false;
    private static boolean hasSpecial= false;

    private static String[] uppercaseStrings = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private static String[] lowercaseStrings = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    private static String[] numbersStrings = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private static String[] specialsStrings = {"!", "?", "@", "(", ")", "{", "}", "[", "]", "/", "=", "~", "$", "%", "&", "#", "*", "-"};

    private static ArrayList<String> passwordChars = new ArrayList<>();

    private static int passwordLength;

    private static String time;

    private static int hours = 0;
    private static int days = 0;
    private static int month = 0;
    private static int years = 0;

    private static String securityLevel = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety_check);

        setButtonWidth();
    }

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

    public void buttonGoToGeneratePassword(View v) {

        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    public void buttonGoToEasyPassword(View v) {

        startActivity(new Intent(getApplicationContext(), ActivityEasyRemember.class));
    }

    /*
    public void buttonGoToCheckSafety(View v) {

        startActivity(new Intent(getApplicationContext(), ActivitySafetyCheck.class));
    }
    */

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

    public void buttonCheckPasswordSecurity(View v) {

        EditText editTextPassword = (EditText)findViewById(R.id.editTextPassword);

        password = editTextPassword.getText().toString();

        if (password.length() == 0) {
            Toast.makeText(ActivitySafetyCheck.this, "No Password entered!", Toast.LENGTH_SHORT).show();
        }

        System.out.println("password: " + password);

        passwordLength = password.length();
        System.out.println("password length: " + passwordLength);

        checkPassword();



        //System.out.println(passwordChars);


    }

    public void checkPassword() {

        // Check what chars are contained in password

        if (!password.equals(password.toLowerCase())) {
            hasUppercase = true;
        }

        if (!password.equals(password.toUpperCase())) {
            hasLowercase = true;
        }

        if (password.matches(".*\\d+.*")) {
            hasNumber = true;
        }

        if (!password.matches("[A-Za-z0-9]*")) {
            hasSpecial = true;
        }

        fillArrayList();
    }

    public void fillArrayList() {

        // Add chars of password to one list

        passwordChars.clear();

        if (hasUppercase) {

            for (int i = 0; i < uppercaseStrings.length; i++) {

                passwordChars.add(uppercaseStrings[i]);
            }
        }

        if (hasLowercase) {

            for (int i = 0; i < lowercaseStrings.length; i++) {

                passwordChars.add(lowercaseStrings[i]);
            }
        }

        if (hasNumber) {

            for (int i = 0; i < numbersStrings.length; i++) {

                passwordChars.add(numbersStrings[i]);
            }
        }

        if (hasSpecial) {

            for (int i = 0; i < specialsStrings.length; i++) {

                passwordChars.add(specialsStrings[i]);
            }
        }

        calculateTime();
    }

    public void calculateTime() {

        int charsetLength = passwordChars.size();
        System.out.println("charset length: " + charsetLength);

        long possibleCombinations = (int) (Math.pow(charsetLength, passwordLength));
        System.out.println("possible combinations: " + possibleCombinations);

        int keysPerSecond = 250000;

        int timeToHack = (int) (possibleCombinations/keysPerSecond);
        System.out.println("Seconds to hack Password: " + timeToHack);

        int seconds = timeToHack % 60;
        int minutes = (timeToHack % 3600) / 60;
        hours = timeToHack / 3600;
        days = hours / 24;
        month = days / 30;
        years = month / 12;

        time = years + " years " + month + " month " + days + " days " + hours + " hours " + minutes + " minutes " + seconds + " seconds";

        setTime();
    }

    public void setTime() {

        TextView textViewDuration = (TextView)findViewById(R.id.textViewDuration);

        textViewDuration.setText(time);

        setSecurityLevel();
    }

    public void setSecurityLevel() {

        TextView textViewSecurityLevelValue = (TextView)findViewById(R.id.textViewSecurityLevelValue);

        securityLevel = "Security Level";

        if (years < 0) {
            securityLevel = "5 - careless";
        }

        else if (month < 0) {
            securityLevel = "4 - low";
        }

        else if (days < 0) {
            securityLevel = "3 - medium";
        }

        else if (hours < 0) {
            securityLevel = "2 - high";
        }

        else {
            securityLevel = "1 - very high";
        }

        textViewSecurityLevelValue.setText(securityLevel);
    }
}
