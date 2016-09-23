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

public class ActivitySafetyCheck extends AppCompatActivity {

    private static String password;

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

        calculateTime();

    }

    public void calculateTime() {

        int charsetLength = 80;
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
