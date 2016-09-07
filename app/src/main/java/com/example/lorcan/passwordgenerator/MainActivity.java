package com.example.lorcan.passwordgenerator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //commentary
    }

    public void buttonClicked(View v) {

        /*
        Button button = (Button) v;
        ((Button) v).setText("clicked");
        */

        TextView tv = (TextView)findViewById(R.id.textView2);
        tv.setText("New Password");
    }
}
