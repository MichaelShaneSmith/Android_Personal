package com.example.myfirstapp;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.OrientationEventListener;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;



public class DoTheThing extends Activity {
    MediaPlayer mp;
    TextView textviewOrientation;
    OrientationEventListener myOrientationEventListener;
    boolean flag = false;
    long[] pattern = {0,1000};
    PowerManager.WakeLock wakeLock;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_the_thing);
        final Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        mp= MediaPlayer.create(this, R.raw.sound);

        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "My wakelock");
        wakeLock.acquire();


        randomNumber();



        textviewOrientation = (TextView)findViewById(R.id.textorientation);

        myOrientationEventListener
                = new OrientationEventListener(this, SensorManager.SENSOR_DELAY_NORMAL){

            @Override
            public void onOrientationChanged(int arg0) {
                // TODO Auto-generated method stub
                textviewOrientation.setText("Orientation: " + String.valueOf(arg0));


                if((arg0 >= 95 && arg0 <= 180) && flag == false)
                {
                    //v.vibrate(500);
                    v.vibrate(pattern, -1);

                    mp.start();
                    flag = true;
                }

                if((arg0 >= 350 && arg0 <= 360) || (arg0 >= 0 && arg0 <= 65) && flag==true) {

                    flag = false;
                }

            }};



        if (myOrientationEventListener.canDetectOrientation())
        {
            //Toast.makeText(this, "Landscape", Toast.LENGTH_LONG).show();
            myOrientationEventListener.enable();

        }
        else{
            Toast.makeText(this, "Can't DetectOrientation", Toast.LENGTH_LONG).show();
            finish();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_do_the_thing, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void randomNumber()
    {
        Random rand = new Random();  // define number generator
        int pick;
        pick = rand.nextInt(10);
        //System.out.println(pick);
        Toast.makeText(getBaseContext(), "Random Number: "+ String.valueOf(pick), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed()
    {
        myOrientationEventListener.disable();
        super.onBackPressed();
    }



}
