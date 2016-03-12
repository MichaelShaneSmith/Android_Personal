package com.example.myfirstapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;


public class Off extends Activity{
    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;

    private ToggleButton vibToggle;
    private ToggleButton soundToggle;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);


        vibToggle = (ToggleButton) findViewById(R.id.vibToggle);
        soundToggle = (ToggleButton) findViewById(R.id.soundToggle);


        //set the switch to ON
        vibToggle.setChecked(false);
        soundToggle.setChecked(false);

    }


    @Override
    public void onBackPressed()
    {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
        {
            super.onBackPressed();
            return;
        }
        else { Toast.makeText(getBaseContext(), "Press again to exit", Toast.LENGTH_SHORT).show(); }

        mBackPressed = System.currentTimeMillis();
    }


    public void testButton(View view)
    {
        Intent intent = new Intent(this, DoTheThing.class);
        startActivity(intent);
    }

    public void vibButton(View view)
    {
        Intent intent = new Intent(this, Vibrate.class);
        startActivity(intent);
        finish();
    }

    public void soundButton(View view)
    {
        Intent intent = new Intent(this, Sound.class);
        startActivity(intent);
        finish();
    }

}