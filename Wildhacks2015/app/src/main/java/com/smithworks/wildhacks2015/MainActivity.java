package com.smithworks.wildhacks2015;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class MainActivity extends AppCompatActivity {
    Button mButton;
    EditText mEdit;
    EditText mEdit2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1);
        Firebase.setAndroidContext(this);
        Firebase myFirebaseRef = new Firebase("https://classchat2015.firebaseio.com/");

        //getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(toolbar);
        //setTitle("ClassChat");
        toolbar.setLogo(R.mipmap.ic_launcher);

        //int titleId = getResources().getIdentifier("action_bar_title", "id","android");
        mButton = (Button)findViewById(R.id.button);
        mEdit   = (EditText)findViewById(R.id.editText);
        mEdit2   = (EditText)findViewById(R.id.editText2);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.About) {
            Intent intent = new Intent(this, about_team.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void chatScreen(View view)
    {
        String name = MainActivity.this.mEdit.getText().toString();
        String course = MainActivity.this.mEdit2.getText().toString();

        Toast.makeText(getBaseContext(), "Hey, " + name + "!", Toast.LENGTH_LONG).show();
        Toast.makeText(getBaseContext(), "Welcome to the " + course + " ClassChat!", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, ChatPage.class);
        intent.putExtra("name", name);
        intent.putExtra("course", course);
        startActivity(intent);
    }
}
