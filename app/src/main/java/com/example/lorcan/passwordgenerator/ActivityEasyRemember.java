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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class ActivityEasyRemember extends AppCompatActivity {

    private static ArrayList<String> arrayListWords = new ArrayList<>();

    private static String separator = "";

    private static String easyPassword = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_remember);

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

                Toast.makeText(getApplicationContext(), item.toString(),Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getApplicationContext(), ActivitySettings.class));

                break;

            case R.id.item_option2_help:

                Toast.makeText(getApplicationContext(), item.toString(),Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getApplicationContext(), ActivityHelp.class));

                break;

            case R.id.item_option3_feedback:

                Toast.makeText(getApplicationContext(), item.toString(),Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getApplicationContext(), ActivityFeedback.class));

                break;

            case R.id.item_option4_about:

                Toast.makeText(getApplicationContext(), item.toString(),Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getApplicationContext(), ActivityAbout.class));

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void buttonGoToGeneratePassword(View v) {

        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    /*
    public void buttonGoToEasyPassword(View v) {

        startActivity(new Intent(getApplicationContext(), ActivityEasyRemember.class));
    }
    */

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

    public void buttonGenerateEasyPasswordClicked(View v) {

        RadioButton radioButtonHash = (RadioButton)findViewById(R.id.radioButtonHash);
        RadioButton radioButtonAnd = (RadioButton)findViewById(R.id.radioButtonAnd);
        RadioButton radioButtonPlus = (RadioButton)findViewById(R.id.radioButtonPlus);

        if (radioButtonHash.isChecked() || radioButtonAnd.isChecked() || radioButtonPlus.isChecked()) {

            getClickedRadioButton(radioButtonHash, radioButtonAnd, radioButtonPlus);
        }

        else {
            Toast.makeText(ActivityEasyRemember.this, "Choose one RadioButton!", Toast.LENGTH_LONG).show();
        }
    }

    public void buttonCopyEasyPasswordClicked(View v) {

        System.out.println("Button to Copy Easy Password clicked!");

        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("copy easy password", easyPassword);
        clipboardManager.setPrimaryClip(clipData);

        Toast.makeText(ActivityEasyRemember.this, "Easy Password copied!", Toast.LENGTH_LONG).show();
    }

    public void getClickedRadioButton(RadioButton radioButtonHash, RadioButton radioButtonAnd, RadioButton radioButtonPlus) {

        if (radioButtonHash.isChecked()) {
            separator = "#";
        }

        if (radioButtonAnd.isChecked()) {
            separator = "&";
        }

        if (radioButtonPlus.isChecked()) {
            separator = "+";
        }

        System.out.println("Chosen separator: " + separator);


        addToArrayList();
    }

    public void addToArrayList() {

        arrayListWords.clear();

        /*
         * 100 Most Common English Nouns
         * from "http://www.linguasorb.com/english/most-common-nouns/"
         */

        String[] nouns = {

                "time",	"issue", "year", "side", "people", "kind", "way", "head", "day", "house", "man", "service",
                "thing", "friend", "woman", "father", "life", "power", "child", "hour", "world", "game", "school",
                "state", "end", "family", "member", "student", "law", "group", "car", "country", "city", "problem",
                "community", "hand", "name", "part", "president", "place", "team", "case", "minute", "week", "idea",
                "company", "kid", "system", "body", "program", "information", "question", "back", "work", "parent",
                "government", "face", "number", "others", "night", "level", "Mr", "office", "point", "door", "home",
                "health", "water", "person", "room", "art", "mother", "war", "area", "history", "money", "party",
                "storey", "result", "fact", "change", "month", "morning", "lot", "reason", "right", "research", "line",
                "girl", "book", "guy", "eye", "food", "job", "moment", "word", "air", "business", "teacher", "study"
        };

        for (int i = 0; i <nouns.length; i++) {

            arrayListWords.add(nouns[i]);
        }

        System.out.println(arrayListWords);

        makeEasyPassword();

    }

    public void makeEasyPassword() {

        easyPassword = "";

        for (int i = 0; i < 3; i++) {
            Random randomizer = new Random();
            String random = arrayListWords.get(randomizer.nextInt(arrayListWords.size()));
            easyPassword += random;
            easyPassword += separator;
        }

        easyPassword = easyPassword.substring(0, (easyPassword.length() - 1));

        System.out.println(easyPassword);

        displayEasyPassword();
    }

    public void displayEasyPassword() {

        Button buttonCopyEasyPassword = (Button)findViewById(R.id.buttonCopyEasyPassword);

        TextView textViewEasyPassword = (TextView)findViewById(R.id.textViewEasyPassword);
        textViewEasyPassword.setText(easyPassword);

        buttonCopyEasyPassword.setVisibility(View.VISIBLE);
    }
}
