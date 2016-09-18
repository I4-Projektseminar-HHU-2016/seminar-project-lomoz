package com.example.lorcan.passwordgenerator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class ActivitySettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
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

            case R.id.item_option2_help:
                Toast.makeText(getApplicationContext(), item.toString(),Toast.LENGTH_SHORT).show();

                break;

            case R.id.item_option3_feedback:
                Toast.makeText(getApplicationContext(), item.toString(),Toast.LENGTH_SHORT).show();

                break;

            case R.id.item_option4_about:
                Toast.makeText(getApplicationContext(), item.toString(),Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
