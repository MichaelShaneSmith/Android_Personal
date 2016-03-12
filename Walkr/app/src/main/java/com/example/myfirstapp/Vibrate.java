package com.example.myfirstapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Random;


public class Vibrate extends Activity{
    private static final int TIME_INTERVAL = 2000;
    //long[] pattern = {125,75,125,275,200,275,125,75,125,275,200,600,200,600};
    long[] pattern = {0,500};
    private long mBackPressed;
    boolean flag = false;
    Vibrator v;

    private ToggleButton vibToggle;
    private ToggleButton soundToggle;
    OrientationEventListener myOrientationEventListener;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        randomPattern();

        vibToggle = (ToggleButton) findViewById(R.id.vibToggle);
        soundToggle = (ToggleButton) findViewById(R.id.soundToggle);


        //set the switch to ON
        vibToggle.setChecked(true);
        soundToggle.setChecked(false);



        myOrientationEventListener
                = new OrientationEventListener(this, SensorManager.SENSOR_DELAY_NORMAL){

            @Override
            public void onOrientationChanged(int arg0) {
                // TODO Auto-generated method stub


                if((arg0 >= 95 && arg0 <= 180) && flag == false)
                {
                    v.vibrate(pattern, -1);
                    flag = true;
                }

                if((arg0 >= 350 && arg0 <= 360) || (arg0 >= 0 && arg0 <= 65) && flag==true) {

                    flag = false;
                }

            }};



        if (myOrientationEventListener.canDetectOrientation())
        {
            myOrientationEventListener.enable();


        }
        else{
            Toast.makeText(this, "Can't DetectOrientation", Toast.LENGTH_LONG).show();
            finish();
        }

    }

    public void randomPattern()
    {
        Random rand = new Random();  // define number generator
        int pick;
        pick = rand.nextInt(4);
        pick++;
        Toast.makeText(getBaseContext(), "Sound #"+ String.valueOf(pick), Toast.LENGTH_LONG).show();

        if(pick==1)
        {
            pattern = new long[] {0,500,0,0,0,0,0,0};
        }
        if(pick==2)
        {
            pattern = new long[] {0,500,100,500,0,0,0,0};
        }
        if(pick==3)
        {
            pattern = new long[] {0,500,100,500,100,500,0,0};
        }
        if(pick==4)
        {
            pattern = new long[] {0,500,100,500,100,500,100,500};
        }
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
        myOrientationEventListener.disable();
        Intent intent = new Intent(this, Off.class);
        startActivity(intent);
        finish();
    }

    public void soundButton(View view)
    {
        myOrientationEventListener.disable();
        Intent intent = new Intent(this, MyActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        v.cancel();

    }


}