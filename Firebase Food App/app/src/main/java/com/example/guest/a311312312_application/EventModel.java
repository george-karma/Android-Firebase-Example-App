package com.example.guest.a311312312_application;

import java.util.ArrayList;

/**
 * Created by Danut Nitu-25182962 on 26/01/2018.
 */

//We use this class to model java objects, through which we communicate with the JSON database
public class EventModel
{
    //Declaring strings which will be put in the java objects we create.
    private  String clientName;
    private String numberOfPeople;
    private String course1;
    private String course2;
    private String course3;
    private String course4;

    public EventModel(){}

    //The method bellow is used to create java objects and to get/set items to communicate with the BaseAdapter class from "CustomListAdapter.class"
    public EventModel(String clientName,String numberOfPeople, String course1, String course2, String course3, String course4)
    {
        this.clientName = clientName;
        this.numberOfPeople = numberOfPeople;
        this.course1 = course1;
        this.course2 = course2;
        this.course3 = course3;
        this.course4 = course4;
    }

    //Get/set for the necessary variables.
    public String getClientName()
    {
        return clientName;
    }
    public void  setClientName(String clientName)
    {
     this.clientName = clientName;
    }


    public String getNumberOfPeople()
    {
        return numberOfPeople;
    }
    public  void setNumberOfPeople(String numberOfPeople)
    {
        this.numberOfPeople = numberOfPeople;
    }

    public String getCourse1(){return course1;}
    public  void setCourse1(String course1){this.course1 = course1;}

    public  String getCourse2(){return  course2;}
    public void setCourse2(String course2){this.course2 = course2;}

    public String getCourse3(){return course3;}
    public void setCourse3(String course3){this.course3 = course3;}

    public String getCourse4(){return course4;}
    public void setCourse4(String course4){this.course4 = course4;}



}
