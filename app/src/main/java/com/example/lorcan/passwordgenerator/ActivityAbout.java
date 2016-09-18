package com.example.lorcan.passwordgenerator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class ActivityAbout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
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

            /*
            case R.id.item_option4_about:

                Toast.makeText(getApplicationContext(), item.toString(),Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getApplicationContext(), ActivityAbout.class));

                break;
            */
        }

        return super.onOptionsItemSelected(item);
    }


    public void buttonGoToGeneratePassword(View v) {

        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    public void buttonGoToEasyPassword(View v) {

        startActivity(new Intent(getApplicationContext(), ActivityEasyRemember.class));
    }

    public void buttonGoToCheckSafety(View v) {

        startActivity(new Intent(getApplicationContext(), ActivitySafetyCheck.class));
    }
}
