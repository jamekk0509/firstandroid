package com.qinglong.day1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "James: MainA";
    //Add the View here.
    TextView myTextView = null;
    ImageView myImageView;
    Spinner mySpinner;
    EditText myEditText;
    Button myButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(findViewById(R.id.toolbar));

        //EXAMPLE OF HOW TO ASSOCIATE XML -> CODES
        myTextView = findViewById(R.id.textView);
        myImageView = findViewById(R.id.imageView);
        mySpinner = findViewById(R.id.spinner);
        myEditText = findViewById(R.id.editText);
        myButton = findViewById(R.id.button);

        //Example of how to add things to logCat
        int heartrate = 45;
        Log.d(TAG, "onCreate: James was here. HR=" + heartrate);
        Log.i(TAG, "onCreate: James was here.");
        Log.w(TAG, "onCreate: James was here.");
        Log.e(TAG, "onCreate: James was here.");

        //making changes to the views.
        myButton.setText("CLICK ME");
        myButton.setTextColor(Color.BLACK);
        myTextView.setTextColor(Color.WHITE);
        //you can also set visibility of your view.
        myButton.setVisibility(View.INVISIBLE);
        //after 5 seconds, show the button.
//        myButton.postDelayed(
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        myButton.setVisibility(View.VISIBLE);
//                    }
//                }
//                , 5000);
        myButton.postDelayed(
                () -> myButton.setVisibility(View.VISIBLE)
                , 5000);


        //example of how to do the callbacks.
        //click text view to change to default.
        myTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myTextView.setText(R.string.button);
            }
        });
        //click button to populate the textview with the text in edittext
        myButton.setOnClickListener((v) -> {
            String userInput = myEditText.getText().toString();
            myTextView.setText(userInput);
            //send a string out.
            sendTextIntentExample(userInput);
        });

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //examples of how to create pop-ups. (3 types)
                String textToPopup = "You have selected: " + mySpinner.getItemAtPosition(position).toString();
//                if (position == 0) {
//                } else if (position == 1) {
//                } else if (position == 2) {
//                } else {
//                }
                switch (position) {
                    case 0:
                    case 1:
                        showSnackbarExample(textToPopup);
                        break;
                    case 2:
                    default:
                        showAlertDialog(textToPopup);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });




    /*
    TO CHANGE LAUNCHER ICON (NEW> IMAGE ASSET)
    TO ADD DRAWABLES (NEW> VECTOR ASSET)
     */

        //TODO: RECAP FOR CALLBACKS (JAVA PROGRAMMING)
        //anonymous class example
        String newName = changeName(new Callback() {
            @Override
            public String onClick(String input) {
                return handleJavaCallback();
            }
        }).toUpperCase();
        //lambda class example
        String newName2 = changeName((xxx) -> handleJavaCallback()).toUpperCase();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onResume() {

        Log.d(TAG, "onResume: ");
        super.onResume();
    }

    @Override
    protected void onDestroy() {

        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }

    private void sendTextIntentExample(String userInput) {
        //refer to https://developer.android.com/training/sharing/send#send-text-content
        Intent textIntent = new Intent();

        textIntent.setAction(Intent.ACTION_SEND);
        textIntent.putExtra(Intent.EXTRA_TEXT, "James's app: " + userInput);
        textIntent.setType("text/plain"); //select the MIME type
/*
Optionally, you can add extras to include more information, such as email recipients
(EXTRA_EMAIL, EXTRA_CC, EXTRA_BCC), the email subject (EXTRA_SUBJECT), and so on.777
 */

        Intent shareIntent = Intent.createChooser(textIntent, "SELECT APP");
        startActivity(shareIntent);

    }

    private void showSnackbarExample(String textToPopup) {
        Snackbar.make(mySpinner, textToPopup, Snackbar.LENGTH_LONG).show();
    }

    private void showAlertDialog(String textToPopup) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.app_name)
                .setMessage(textToPopup)
                .setIcon(R.mipmap.ic_launcher)
                .create()
                .show();
    }

    //example of using MENU (APPBAR)
    //PRESS ALT+INS+OVERRIDE
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();
        if (itemId == R.id.menu_1) {
            showSnackbarExample("Menu 1 clicked");
        } else if (itemId == R.id.menu_2) {
            showSnackbarExample("Menu 2 Clicked");
        } else if (itemId == R.id.menu_3) {
            showSnackbarExample("Menu 3 clicked");
        } else {
            return super.onOptionsItemSelected(item);
        }
        return true;

    }

    //======================
    //java programming recap
    //the following is an example of how to create a callback (listener) using interface.
    //=====================
    private String changeName(Callback callback) {
        return callback.onClick("QL:");
    }

    interface Callback {
        String onClick(String input);
    }

    private String handleJavaCallback() {
        String result = "QingLong";
        return result;
    }

}