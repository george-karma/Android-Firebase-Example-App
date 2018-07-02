package com.example.guest.a311312312_application;

import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Danut Nitu-25182962 on 26/01/2018.
 */

//This class is used to
public class CustomListAdapter extends BaseAdapter
{
    //Declaring the array lists that will store various information about the client and the event.
    private ArrayList<String> clientNames;
    private ArrayList<String> numberOfPeople;
    private ArrayList<String> course1;
    private ArrayList<String> course2;
    private ArrayList<String> course3;
    private ArrayList<String> course4;

    //Declaring an empty activity to store the CustomListAdapter, and then inflate it with the
    //BaseAdapter extension.
    private AppCompatActivity activity;

    private int i = 0;

    //The CustomListAdapter class bellow will be lopped to get and set the list of data off the
    //database.
    public CustomListAdapter(ArrayList<String> clientNames, ArrayList<String>numberOfPeople,
                             ArrayList<String>course1 , ArrayList<String>course2 , ArrayList<String>course3,
                             ArrayList<String>course4,AppCompatActivity activity)
    {
        //"this." is used to loop the method and get/set the data off of the database, node by node.
        this.clientNames = clientNames;
        this.numberOfPeople = numberOfPeople;
        this.course1 = course1;
        this.course2 = course2;
        this.course3 = course3;
        this.course4 = course4;
        this.activity = activity;
    }

    //getCount(),getItem() and getItemId() are used by the BaseAdapter extension for array iteration.
    @Override
    public int getCount()
    {
        return clientNames.size();
    }

    @Override
    public Object getItem(int i)
    {
        return clientNames.get(i);

    }


    @Override
    public long getItemId(int i)
    {
        return 0;
    }

    //The method bellow is used to by the BaseAdapter extension to get where the items on the
    // screen are and then inflate the layout.
    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        //creating the view that goes into the
        view = LayoutInflater.from(activity.getApplicationContext()).inflate(R.layout.event_list,
                viewGroup,false);

        //declaring the TextViews and finding them in the layout
        TextView clientNameTx = view.findViewById(R.id.clientName);
        TextView numberOfPeopleTx = view.findViewById(R.id.numberOfPeople);
        TextView course1Tx = view.findViewById(R.id.course1l);
        TextView course2Tx = view.findViewById(R.id.course2l);
        TextView course3Tx = view.findViewById(R.id.course3l);
        TextView course4Tx = view.findViewById(R.id.course4l);

        //Setting the in-layout text boxes to vales from the arrays, by iterating this process, we
        //we get the list of objects we want.
        numberOfPeopleTx.setText(numberOfPeople.get(i));
        clientNameTx.setText(clientNames.get(i));
        course1Tx.setText(course1.get(i));
        course2Tx.setText(course2.get(i));
        course3Tx.setText(course3.get(i));
        course4Tx.setText(course4.get(i));

        return view;
    }
}
