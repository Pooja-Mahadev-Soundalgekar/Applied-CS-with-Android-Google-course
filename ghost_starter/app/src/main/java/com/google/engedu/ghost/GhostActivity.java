package com.google.engedu.ghost;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;


public class GhostActivity extends ActionBarActivity implements View.OnClickListener{
    private static final String COMPUTER_TURN = "Computer's turn";
    private static final String USER_TURN = "Your turn";
    private FastDictionary dictionary;
    private boolean userTurn = false;
    private Random random = new Random();
    Button reset;
    TextView word;
    TextView status;
    Button challenge;
    private java.lang.String KEY_TEXT_VALUE="yyy";

    public void start_up() {

        String alpha= "abcdefghijklmnopqrstuvwxyz";
        int k = random.nextInt(26);

        String s = dictionary.getAnyWordStartingWith(alpha.charAt(k)+"");

       // String upTo4Characters = s.substring(0, Math.min(s.length(), 4));
        //word.setText(upTo4Characters);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghost);

        reset = (Button) findViewById(R.id.reset);
        reset.setOnClickListener(this);
        challenge = (Button) findViewById(R.id.challenge);
        challenge.setOnClickListener(this);
        word=(TextView) findViewById(R.id.word);
        status=(TextView) findViewById(R.id.status);
        AssetManager assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open("words.txt");
            dictionary = new FastDictionary(inputStream);

        } catch (IOException e) {
            Toast toast = Toast.makeText(this, "Could not load dictionary", Toast.LENGTH_LONG);
            toast.show();
        }

       // start_up();
        onStart(null);

        if (savedInstanceState != null) {

            CharSequence savedText = savedInstanceState.getCharSequence(KEY_TEXT_VALUE);
            word.setText(savedText);
        }

    }
    @Override
    protected void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence(KEY_TEXT_VALUE, word.getText());
    }
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        char x= (char) event.getUnicodeChar();
        if(x>='a' && x<='z') {

            String s = word.getText().toString();
            s = s + Character.toString(x);
            word.setText(s);

            if(dictionary.isWord(s))
            {
                status.setText("Computer Won!");
            }
            else
            {
                status.setText(COMPUTER_TURN);
                computerTurn();
            }
        }
        else
        {
            return super.onKeyUp(keyCode, event);
        }
        return true;


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ghost, menu);
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


    private void computerTurn() {
        TextView label = (TextView) findViewById(R.id.status);
        // Do computer turn stuff then make it the user's turn again
        userTurn = true;
        label.setText(USER_TURN);

        String input =  word.getText().toString();
        if(input.equals(""))
        {
            String newWord;
            newWord = dictionary.getAnyWordStartingWith(input);

            if(newWord == null)
            {
                // computer wins
                status.setText("Computer wins");
            }
            else {
                char c = newWord.charAt(input.length());
                input += String.valueOf(c);

                word.setText(input);
            }
        }
        else
        {
            if (input.length() >= 4 && dictionary.isWord(input)) {
                status.setText("Computer wins");
            }
            else
            {
                String newWord;
                newWord = dictionary.getAnyWordStartingWith(input);

                if(newWord == null)
                {
                    // computer wins
                    status.setText("Computer wins");
                }
                else {
                    char c = newWord.charAt(input.length());
                    input += String.valueOf(c);

                    word.setText(input);
                }
            }
        }

    }

    /**
     * Handler for the "Reset" button.
     * Randomly determines whether the game starts with a user turn or a computer turn.
     * @param view
     * @return true
     */
    public boolean onStart(View view) {
        userTurn = random.nextBoolean();
        word.setText("");
        //start_up();

        TextView label = (TextView) findViewById(R.id.status);
        if (userTurn) {
            label.setText(USER_TURN);
        } else {
            label.setText(COMPUTER_TURN);
            computerTurn();
        }
        return true;
    }
    public void challenge(View v)
    {
        String computerWord = word.getText().toString();
        String temp;

        if(computerWord.length()>=4 && dictionary.isWord(computerWord))
        {
            status.setText("User wins");
        }
        else if(!dictionary.isWord(computerWord)){

            if((temp = dictionary.getAnyWordStartingWith(computerWord))==null)
            {
                status.setText("User wins");
            }
            else
            {
                status.setText("Computer wins");
                word.setText(temp);
            }
        }
    }
    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.reset: onStart(v);
                break;
            case R.id.challenge:challenge(v);
        }
    }
}
