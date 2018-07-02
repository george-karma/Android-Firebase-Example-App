package com.example.guest.a311312312_application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//This class is just used to get input from the user
public class AddEventPage extends AppCompatActivity implements View.OnClickListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event_page);
        Button addEvent = (Button) findViewById(R.id.addEventBtn);
        addEvent.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        //TODO: ADD EXCEPTION CASES IF THEY HAVE LESS THAN 4 COURSES
        //Getting the input off the screen
        String clientName = ((EditText) findViewById(R.id.clientName)).getText().toString().trim();
        String numberOfPeople = ((EditText) findViewById(R.id.numberOfClients)).getText().toString().trim();
        String course1 = ((EditText) findViewById(R.id.course1)).getText().toString().trim();
        String course2 = ((EditText) findViewById(R.id.course2)).getText().toString().trim();
        String course3 = ((EditText) findViewById(R.id.course3)).getText().toString().trim();
        String course4 = ((EditText) findViewById(R.id.course4)).getText().toString().trim();
        String price1 = ((EditText) findViewById(R.id.price1)).getText().toString().trim();
        String price2 = ((EditText) findViewById(R.id.price2)).getText().toString().trim();
        String price3 = ((EditText) findViewById(R.id.price3)).getText().toString().trim();
        String price4 = ((EditText) findViewById(R.id.price4)).getText().toString().trim();

        if (clientName.isEmpty() || numberOfPeople.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please fill in the event name and the number of clients",
                    Toast.LENGTH_SHORT).show();
        } else {

            //Creating the return intent to go back to the previous activity and move the input between activities.
            Intent returnIntent = new Intent();

            //Setign the input inside the intent so we can move between pages.
            returnIntent.putExtra("clientName", clientName);
            returnIntent.putExtra("numberOfPeople", numberOfPeople);
            returnIntent.putExtra("course1", course1);
            returnIntent.putExtra("course2", course2);
            returnIntent.putExtra("course3", course3);
            returnIntent.putExtra("course4", course4);
            returnIntent.putExtra("price1", price1);
            returnIntent.putExtra("price2", price2);
            returnIntent.putExtra("price3", price3);
            returnIntent.putExtra("price4", price4);
            //Setign the result.
            setResult(Home.RESULT_OK, returnIntent);

            //Moving back to the previous activity.
            finish();
        }
    }
}

