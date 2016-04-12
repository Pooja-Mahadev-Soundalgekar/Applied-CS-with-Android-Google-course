package com.example.poojams.dicegame;

import android.net.Uri;
import android.os.Bundle;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonroll;
    ImageView roll;
    Button buttonhold;
    Button button3;
    int usertotal,userturn,comptotal,compturn;
    String y="Your Score: ";
    String c="Computer Score: ";
    String ct="Computer Total Score: ";
    TextView yourscore;
    TextView computerscore;
    TextView computertotalscore;

   // Button Rollbtn,Holdbtn,Resetbtn;
    ImageView image;
    TextView tv;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
   // private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        buttonroll = (Button) findViewById(R.id.buttonroll);
        button3 = (Button) findViewById(R.id.button3);
        buttonhold=(Button)findViewById(R.id.buttonhold);
       // yourscore=(TextView)findViewById(R.id.textView);
        image= (ImageView) findViewById(R.id.roll);
        tv=(TextView) findViewById(R.id.textView);
        //computerscore=(TextView)findViewById(R.id.computerscore);
        //computertotalscore=(TextView)findViewById(R.id.computertotalscore);
        buttonhold.setOnClickListener(this);
        buttonroll.setOnClickListener(this);
        roll=(ImageView)findViewById(R.id.roll);

        button3.setOnClickListener(this);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public void clockwise(View view){
        ImageView image = (ImageView)findViewById(R.id.roll);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.myanimation);
        image.startAnimation(animation);
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
    public void reset()
    {
       // Toast.makeText(getApplicationContext(),"hello reset",Toast.LENGTH_SHORT).show();
        usertotal=0;
        userturn=0;
        compturn=0;
        comptotal=0;
        tv.setText("Your score:" + usertotal + "Computer score:" + comptotal + "Your turn score:" + userturn);
    }

    void winner()
    {
        if(usertotal>=100) {
            Toast.makeText(getApplicationContext(), "User Wins!", Toast.LENGTH_SHORT).show();
            reset();
        }
        else if(comptotal>=100) {
            Toast.makeText(getApplicationContext(), "Computer Wins!", Toast.LENGTH_SHORT).show();
            reset();
        }

    }
    public int helper()
    {
        int k=1 + (int)(Math.random() * 6);
        if(k==1)
            roll.setImageResource(R.drawable.dice1);
        else if(k==2)
            roll.setImageResource(R.drawable.dice2);
        else if(k==3)
            roll.setImageResource(R.drawable.dice3);
        else if(k==4)
            roll.setImageResource(R.drawable.dice4);
        else if(k==5)
            roll.setImageResource(R.drawable.dice5);
        else
            roll.setImageResource(R.drawable.dice6);
        return k;
    }
    void cleaning()
    {
        comptotal=comptotal+compturn;

        compturn=0;
        tv.setText("Your score:" + usertotal + "Computer score:" + comptotal + "computer turn score:" + compturn);
        winner();
        buttonroll.setEnabled(true);
        buttonhold.setEnabled(true);
    }
    void computerturn()
    {

        buttonroll.setEnabled(false);
        buttonhold.setEnabled(false);

        final Handler timerHandler = new Handler();
        Runnable timerRunnable = new Runnable() {

            @Override
            public void run() {
                if(compturn<20) {
                    int k = helper();

                    compturn = k + compturn;

                    tv.setText("Your score:" + usertotal + "Computer score:" + comptotal + "computer turn score:" + compturn);

                    timerHandler.postDelayed(this, 500);
                }
                else
                {
                    winner();
                    cleaning();
                    timerHandler.removeCallbacks(this);
                }
            }
        };
        timerHandler.postDelayed(timerRunnable,500);


    }
    public void buttonroll()
    {
        //image.startAnimation(animation);
        int k=helper();
        if(k!=1)
        {
            userturn=k+userturn;

            tv.setText("Your score:"+ usertotal + "Computer score:"+ comptotal + "Your turn score:" + userturn);
        }
        else
        {

            userturn=0;

            tv.setText("Your score:"+ usertotal + "Computer score:"+ comptotal + "Your turn score:" + userturn);
            compturn=0;
            computerturn();
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.buttonroll: buttonroll();

                break;
            case R.id.button3: reset();

                break;
            case R.id.buttonhold: usertotal=usertotal+userturn;
                userturn=0;
                tv.setText("Your score:"+ usertotal + "Computer score:"+ comptotal + "Your turn score:" + userturn);
                winner();
                compturn=0;
                computerturn();
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

    }
}