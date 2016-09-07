package com.example.lorcan.passwordgenerator;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static boolean uppercaseOn = false;
    private static boolean lowercaseOn = false;
    private static boolean numbersOn = false;
    private static boolean specialsOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //commentary
    }

    public void buttonClicked(View v) {

        TextView tv = (TextView)findViewById(R.id.textView2);
        tv.setText("New Password");
    }

    public void kleinbuchstabenChecked(View v) {
        //code to check if this checkbox is checked!
        CheckBox checkBox = (CheckBox)v;
        if(checkBox.isChecked()){

            lowercaseOn = true;
        }
    }

    public void großbuchstabenChecked(View v) {
        //code to check if this checkbox is checked!
        CheckBox checkBox = (CheckBox)v;
        if(checkBox.isChecked()){

            uppercaseOn = true;
        }
    }

    public void numbersChecked(View v) {
        //code to check if this checkbox is checked!
        CheckBox checkBox = (CheckBox)v;
        if(checkBox.isChecked()){

            numbersOn = true;
        }
    }

    public void specialsChecked(View v) {
        //code to check if this checkbox is checked!
        CheckBox checkBox = (CheckBox)v;
        if(checkBox.isChecked()){

            specialsOn = true;
        }
    }
}
