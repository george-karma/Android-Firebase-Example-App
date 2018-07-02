package com.example.guest.a311312312_application;

import android.content.Intent;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Danut Nitu-25182962 on 26/01/2018.
 */

//Implemented On.ClickListener to manage what the buttons on the screen do.
public class Home extends AppCompatActivity implements View.OnClickListener
{

    //Declaring a reference to the database.
    private DatabaseReference myRef;

    //Declaring a reference to the ListView onscreen.
    ListView list;

    String clientName;
    String numberOfPeople;
    String course1;
    String course2;
    String course3;
    String course4;
    String price1;
    String price2;
    String price3;
    String price4;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Setting offline persistence to true so data is available offline.
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        //Initialising the database reference by getting a reference to the location of the "Events"
        //node in the JSON tree. We will store all the events under that node.
        myRef = FirebaseDatabase.getInstance().getReference("Events");

        //Declaring and initializing the buttons to add OnClickListener events on them.
        //We need to cast the buttons to (Button) because they are different elements in
        //AndroidStudio version used for this application

        Button newBtn = (Button) findViewById(R.id.newBtn);

        //Adding OnClickListener events to the buttons

        newBtn.setOnClickListener(this);


        //Initialising the ListView reference.
        //We have to cast to (ListView) because the application is built on an older version of
        //Android Studio.
        list = (ListView) findViewById(R.id.list);
        readData();
    }


    //This method is used to start the intent for the "AddEventPage.class"
    private void inputStart()
    {

        //Creating the intent to go to the ""AddEventPage.class".
        Intent i = new Intent(getApplicationContext(),AddEventPage.class);
        //Starting the activity to get the result/input.
        startActivityForResult(i,1);
    }

    //This method is used to get/set data from the class  started by "inputStart()" (AddEventPage.class)
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        //If the request code is the same as the request code from "inputStart()"...
        if(requestCode ==1)
        {
            //...check if the results were sucesfully gotten...
            if(resultCode == Home.RESULT_OK)
            {
                //TODO: ADD EXCEPTION CASES IF THEY HAVE LESS THAN 4 COURSES
                clientName = data.getStringExtra("clientName");
                numberOfPeople= "Number of clients:"+" "+ data.getStringExtra("numberOfPeople");

                price1= data.getStringExtra("price1");
                price2= data.getStringExtra("price2");
                price3= data.getStringExtra("price3");
                price4= data.getStringExtra("price4");

                //We merge the two strings to reduce ov erhead by not communicating as manny strings cross-class.
                course1= data.getStringExtra("course1") + " " + price1;
                course2= data.getStringExtra("course2") + " " + price2;
                course3= data.getStringExtra("course3") + " " + price3;
                course4= data.getStringExtra("course4") + " " + price4;

                //Adding exception cases so the list does not look bad/has empty space
                if (course1.isEmpty())
                {
                    course1 = "N/A";
                }
                if (course2.isEmpty())
                {
                    course1 = "N/A";
                }
                if (course3.isEmpty())
                {
                    course3 = "N/A";
                }
                if (course4.isEmpty())
                {
                    course4 = "N/A";
                }
                addEvent();
            }
            //...or if the user canceled/there is no result.
            if (resultCode==Home.RESULT_CANCELED)
            {

            }
        }
    }
    //The method bellow will be called by the user pressing a "Add Event".
    private void addEvent()
    {

        //Creating a java object to communicate with the database.
        EventModel eventModel = new EventModel(clientName,numberOfPeople,course1,course2,course3,course4);

        //The ".push()" method is called to add a node with a unique id to the database.

        String id = myRef.push().getKey();
        //After we create the id we use ".getKey" to get the location of the new node
        myRef.child(id).setValue(eventModel);
        //We call the "readData()" here to update the listView with the newly added event.
        readData();
    }

    //The method bellow will be called when the user presses the "Load Data" button, and at the end of
    //the "addEvent()" method in order to refresh the list, in order to avoid a specific visual bug.
    //The bug consists in the data being displayed twice in the listView.
    private void readData()
    {
        //The bellow array lists are used to communicate with the base adapter.
        final ArrayList clientNamesArr = new ArrayList<>();
        final ArrayList numberOfPeopleArr = new ArrayList<>();
        final ArrayList course1Arr = new ArrayList<>();
        final ArrayList course2Arr = new ArrayList<>();
        final  ArrayList course3Arr = new ArrayList<>();
        final ArrayList course4Arr = new ArrayList<>();

        //Adding an event listener to sense when the changes at the location in "myRef". That is why,
        //the method underneath it is called "onDataChange".
        myRef.addValueEventListener(new ValueEventListener()
        {
            //"DataSnapshot" is a snapshot of the structure at/inside the referenced node location.
            //Fore more information about "DataSnapshot", please visit:
            //https://firebase.google.com/docs/reference/android/com/google/firebase/database/DataSnapshot
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                //Declaring a Firebase iterator to iterate for all children of the specified "Events" node.
                Iterable<DataSnapshot> snapshotIterable = dataSnapshot.getChildren();

                //The iterator bellow creates a form of array, containing references to all the
                //children of "Events" node.
                Iterator<DataSnapshot> iterator = snapshotIterable.iterator();

                //The loops bellow runs for every child under the "Events" node
                while(iterator.hasNext())
                {
                    //Reference to EventModel class.
                    EventModel value = iterator.next().getValue(EventModel.class);

                    //Adding the variables off the database to the arrayList used to display the information.
                    clientNamesArr.add(value.getClientName());
                    numberOfPeopleArr.add(value.getNumberOfPeople());
                    course1Arr.add(value.getCourse1());
                    course2Arr.add(value.getCourse2());
                    course3Arr.add(value.getCourse3());
                    course4Arr.add(value.getCourse4());


                    //Updating the ListAdapter with each individual data-set(node in database)/
                    //".notifyDataSetChanged()" is inherited from BaseAdapter.
                    ((CustomListAdapter)((list).getAdapter())).notifyDataSetChanged();

                }
                //Refreshing the list to properly display the events.

            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });
        //Finally setting the Adapter and passing the necessary variables to display on-screen.
        list.setAdapter(new CustomListAdapter(clientNamesArr,numberOfPeopleArr,course1Arr,course2Arr,course3Arr,course4Arr,this));
    }
    //class implemented for/by OnClickListener
    @Override
    public void onClick(View v)
    {
        //Using a switch statement to get the id of the button, then calling the appropriate method.
        switch (v.getId())
        {


            case  R.id.newBtn:
                inputStart();
                break;
            default:
                break;
        }
    }
}
